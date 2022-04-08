//Imports
import groovy.json.JsonSlurper

//Constants
OWNER = "freshdirectllc"
REPO = "storefrontui"
TOKEN = "SGVuYWR6aV9IYXVyeWxpa0BlcGFtLmNvbTpnaHBfemo5djludDJIT2Y0WVpQQ0hWdDg2c05HaTgxbnJEMTVFcjJv"
//HenadziHaurylik's account here
BASE_BRANCH = "epam_main"

//Investigate parameters
def environment = binding.build.environment
println "Describe environment"
environment.each {
    key, value -> println " $key -> $value ";
}
def activeBranchFull = environment.get('GIT_BRANCH')

// def activeBranchFull = "origin/EPAM_MASTER_test" //For test only

def activeBranch = activeBranchFull.split("/").last()
println "Activity found in $activeBranchFull, shorten name - ${activeBranch}"

//Check if active branch is in PR to BASE_BRANCH
def github = new GithubManager(OWNER, REPO, TOKEN)
def activePr = github.getPrForBranches(BASE_BRANCH, activeBranch)


if (activePr != null) {
    println "Run pipeline for PR =>$activePr"
    JenkinsRunner runner=new JenkinsRunner( "http://jenkins.nj01","EPAM_GQL_BE","epam_deploy_store_dev","EPAM_TOKEN")

    runner.setParams([ branch: activeBranch.head, pr_id:activeBranch.id])
    runner.doGet()
} else {
    println "Skip pipeline"
}


class GithubManager {
    final GITHUB_API = 'https://api.github.com/repos'
    private String owner, repo, token

    GithubManager(String owner, String repo, String token) {
        this.owner = owner
        this.repo = repo
        this.token = token
    }

    Pr getPrForBranches(String baseBranch, headBranch) {
        def scanRes = scanForPrs()
        def pr = scanRes.stream().find { it.base.equalsIgnoreCase(baseBranch) & it.head.equalsIgnoreCase(headBranch) }
        println("Found active pr ->$pr")
        return pr
    }

    List<Pr> scanForPrs() {
        String url = "${GITHUB_API}/${this.owner}/${this.repo}/pulls"
        println "Querying ${url}"
        def text = url.toURL().getText(requestProperties: ['Authorization': "Basic ${this.token}", 'Accept': 'application/vnd.github.v3+json'])
        def json = new JsonSlurper().parseText(text)
        List<Pr> prs = []
        json.each {
            //  println (it.toString()) //for full info research
            def pr = new Pr()
            pr.id = it.id
            pr.head = it.head.ref
            pr.base = it.base.ref
            prs.add(pr)
        }
        return prs
    }

    class Pr {
        String head
        String base
        String id

        String toString() {
            return "Pr [id: $id, head: $head, base: $base]"
        }
    }
}


class JenkinsRunner {
    String url
    Map<String, String> params
    private String cause

    JenkinsRunner(String host, view, job, token) {
        this.url = "$host/view/$view/job/$job/buildWithParameters?token=$token"
    }
    void setParams(Map<String,String>params){
        String paramStr=""
        params.each {paramStr+="&${it.key}=${it.value}"}
        url=url+paramStr
    }

    def doGet() {
        println( "GET $url")
        return   url.toURL().getText()
    }
}

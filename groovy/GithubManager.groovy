//Imports
import groovy.json.JsonSlurper

/**
 //Usage example

 //Constants
 OWNER = "freshdirectllc"
 REPO = "storefrontui"
 TOKEN = "SGVuYWR6aV9IYXVyeWxpa0BlcGFtLmNvbTpnaHBfemo5djludDJIT2Y0WVpQQ0hWdDg2c05HaTgxbnJEMTVFcjJv"
 //HenadziHaurylik's account here
 BASE_BRANCH = "epam_main"

 //Check if active branch is in PR to BASE_BRANCH
 def github = new GithubManager(OWNER, REPO, TOKEN)
 def activePr = github.getPrForBranches(BASE_BRANCH, activeBranch)
 */
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
            pr.id = it.number
            pr.head = it.head.ref
            pr.base = it.base.ref
            prs.add(pr)
        }
        return prs
    }
/**
 * Create PR with id comment with message
 *
 * @param prID - PR id
 * @param message - comment message
 * @return responce code 201 -created
 */
    def createPrComment(String prNumber, String message) {

        String url = "${GITHUB_API}/${this.owner}/${this.repo}/pulls/${prNumber}/reviews"
        URLConnection post = new URL(url).openConnection()
        def body = '{"body":"' + message + '", "event":"REQUEST_CHANGES"}'
        println body
        post.setRequestMethod("POST")
        post.setDoOutput(true)
        post.setRequestProperty("Accept", "application/vnd.github.v3+json")
        post.setRequestProperty("Authorization", "Basic ${this.token}")
        post.getOutputStream().write(body.getBytes("UTF-8"))
        def postRC = post.getResponseCode()
        println(post.getInputStream().getText())
        return postRC
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

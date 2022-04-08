
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

    def doRequest() {
        println( "GET $url")
        return   url.toURL().getText()
    }
}
/**
  //Usage example
 JenkinsRunner runner=new JenkinsRunner( "http://jenkins.nj01","EPAM_GQL_BE","epam_deploy_store_dev","EPAM_TOKEN")

 runner.setParams([ branch:"TUE-CICD-main-test"])
 runner.doRequest()
 */


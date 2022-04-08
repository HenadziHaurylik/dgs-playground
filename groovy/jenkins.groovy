import JenkinsRunnerClass
def jenkins = new JenkinsRunner( "http://jenkins.nj01","EPAM_GQL_BE","epam_deploy_store_dev","EPAM_TOKEN")
jenkins.params=[ BRANCH:"TUE-135"]
println jenkins.doGet()
JenkinsRunner runner=new JenkinsRunner()
runner

import GithubManager
OWNER = "freshdirectllc"
REPO = "storefrontui"
TOKEN = "SGVuYWR6aV9IYXVyeWxpa0BlcGFtLmNvbTpnaHBfemo5djludDJIT2Y0WVpQQ0hWdDg2c05HaTgxbnJEMTVFcjJv"

def manager=new GithubManager(OWNER,REPO,TOKEN)
def prs=manager.scanForPrs()
prs.forEach{println it.toString()}
String prID="2775"
def code=manager.createPrComment(prID,"REQUEST_CHANGES: Comment from API")
println "Comment: $code"

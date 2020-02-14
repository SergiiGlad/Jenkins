boolean call(tag) {
    
    env.GIT_SSH_COMMAND = "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no"
    def gettags = sh(returnStdout: true, script: 'git ls-remote -t ssh://git@github.com/sergiiglad/web.git')
   
    return tag in gettags.readLines()
            .collect { it.split()[1].replaceAll('refs/tags/', '') }   

}
#!groovy

def gettags

sshagent(credentials: ['git_ssh']) {
   env.GIT_SSH_COMMAND = "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no"
   gettags = sh(returnStdout: true, script: 'git ls-remote -t ssh://git@github.com/sergiiglad/web.git')
}

return gettags.readLines()
       .collect { it.split()[1].replaceAll('refs/tags/', '')

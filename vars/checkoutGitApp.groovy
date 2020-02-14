
def call(String tag) {
  checkout([
    $class: 'GitSCM',
    branches: [[name: tag]],
    extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: tag]],
    userRemoteConfigs: [[credentialsId: 'github_git', url: 'https://github.com/SergiiGlad/web.git']]
  ])
}
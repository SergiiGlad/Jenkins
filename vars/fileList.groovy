def call(){

return sh(returnStdout: true, script: libraryResource('list-dir.sh'))
   
}
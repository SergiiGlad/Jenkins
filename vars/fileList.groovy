def call(String path){

def findList=sh(returnStdout: true, script: "find $path -name *.yaml")


return findList.readLines()
         
   
}
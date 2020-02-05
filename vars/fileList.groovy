def call(String path){

def findList=sh(returnStdout: true, script: "find $path -name *.yaml")

def filePathList = []

findList.each {
   println it
   filePathList.add( it )
}
return filePathList
   
}
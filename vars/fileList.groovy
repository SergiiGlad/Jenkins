def call(String path){

def findList=sh(returnStdout: true, script: "find $path -name *.yaml")

def filePathList = []

findList.each {
   println it.substring(2)
   filePathList.add( it.substring( 2 ) )
}
return filePathList
   
}
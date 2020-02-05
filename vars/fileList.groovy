def call(String path){

def findList=sh(returnStdout: true, script: "find $path -name *.yaml")

def filePathList = []

findList.each {
   filePathList.add( it.substring( path.length()+1) )
}
return filePathList
   
}
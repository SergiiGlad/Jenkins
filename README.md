# Jenkins

https://opensource.com/article/18/8/devops-jenkins-2

One of the key ideas of DevOps is infrastructure-as-code—having the infrastructure for your delivery/deployment pipeline expressed in code—just as the products that flow it.

Let's start by talking about the foundations. Jenkins and its plugins make the building blocks available for your pipeline tasks via its own programming steps—the Jenkins domain-specific language (DSL).

## DSL steps

When Jenkins started evolving toward the pipeline-as-code model, some of the earliest changes came in the form of a plugin—the workflow plugin. This included the creation of a set of initial DSL steps that allowed for coding up simple jobs in Jenkins and, by extension, simple pipelines.

## Scripted Pipelines

The original approach for creating pipelines in Jenkins 2 is now referred to as scripted. At the highest level, Scripted Pipelines are wrapped in a node block. Here a __node__ refers to a system that contains the Jenkins agent pieces and can run jobs (formerly referred to as a slave instance).

The node gets mapped to a system by using a __label__. A label is simply an identifier that has been added when configuring one or more nodes in Jenkins (done via the global Manage Nodes section).

## Stages

Although this simple node block is technically valid syntax, Jenkins pipelines generally have a further level of granularity—stages. A stage is a way to divide up the pipeline into logical functional units. It also serves to group DSL steps and Groovy code together to do targeted functionality. 

Each stage in a pipeline also gets its own output area in the new default Jenkins output screen—the __Stage View__. 

## Declarative Pipelines

As the name suggests, declarative syntax is more about declaring what you want in your pipeline and less about coding the logic to do it. It still uses the DSL steps as its base, but includes a well-defined structure around the steps. This structure includes many directives and sections that specify the items you want in your pipeline.

## Jenkinsfiles

Although the Jenkins application is the main environment for developing and running our pipelines, the pipeline code itself can also be placed into an external file named Jenkinsfile. Doing this then allows us to treat our pipeline code as any other text file—meaning it can be stored and tracked in source control and go through regular processes, such as code reviews.



## def vs no def

What it means is that in our context (Jenkins pipeline) you could always define your variable without the def keyword because you are always in a script context and your variables will be bound to the script. However, I think it is good practice to use def keyword because it shows you know when you instantiate your variables, and it also can avoid some problems of duplicate variables definitions (if you define them with the def keyword at least compilation will fail if you defined the same variable twice).

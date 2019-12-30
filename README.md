# Jenkins
DSL groove for Jenkins

## def vs no def

What it means is that in our context (Jenkins pipeline) you could always define your variable without the def keyword because you are always in a script context and your variables will be bound to the script. However, I think it is good practice to use def keyword because it shows you know when you instantiate your variables, and it also can avoid some problems of duplicate variables definitions (if you define them with the def keyword at least compilation will fail if you defined the same variable twice).

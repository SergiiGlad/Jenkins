# Building images with kaniko and GitLab CI/CD

https://docs.gitlab.com/ee/ci/docker/using_kaniko.html

__kaniko is a tool to build container images from a Dockerfile, inside a container or Kubernetes cluster.__
kaniko solves two problems with using the docker-in-docker build method:
* Docker-in-docker requires privileged mode in order to function, which is a significant security concern.
* Docker-in-docker generally incurs a performance penalty and can be quite slow.

## Kaniko

https://github.com/GoogleContainerTools/kaniko#running-kaniko-in-docker

## Jenkins step

https://github.com/jenkinsci/kubernetes-plugin/blob/master/examples/kaniko.groovy

to get config.json after docker login
```
container('docker-dind') {
        withDockerRegistry([credentialsId: 'docker-api-key', url: 'https://index.docker.io/v1/']) {
          sh "find / -name config.json -exec cp '{}' . +" 	
          sh 'cat config.json'
        }
      }
```

to create secret config file from cmd

```
kubectl create secret generic dockercred --type=string —from-file=config.json
```

to create secret config file from yaml manifest

```dockercred.yaml
apiVersion: v1
kind: Secret
metadata:
  name: dockersecret
type: kubernetes.io/dockerconfigjson
stringData:
  config.json: |
    {"auths":{"https://index.docker.io/v1/":{"username":"username","password":"password","email":"nobodyo@gmail.com","auth":"ccccccccccccccccc="}}} 
```    
## Pod template
```
apiVersion: v1
kind: Pod
metadata:
  name: kanikocred
spec:
  restartPolicy: OnFailure
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    command: ['/busybox/cat']
    tty: true
    volumeMounts:
      - name: jenkins-docker-cfg
        mountPath: /kaniko/.docker
  volumes:
  - name: jenkins-docker-cfg
    secret:
      secretName: dockercred
```      

## Private registry

to create volumes secret

https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/#create-a-secret-in-the-cluster-that-holds-your-authorization-token
```
kubectl create secret docker-registry dockercred --docker-server='https://index.docker.io/v1/' --docker-username=sergeyglad --docker-password=<your-pword> --docker-email='gladseo@gmail.com'
```

## Pod template
```
apiVersion: v1
kind: Pod
metadata:
  name: kaniko
spec:
  restartPolicy: OnFailure
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    command: ['/busybox/cat']
    tty: true
    volumeMounts:
      - name: jenkins-docker-cfg
        mountPath: /kaniko/.docker
  volumes:
  - name: jenkins-docker-cfg
    projected:
      sources:
      - secret:
          name: dockercred
          items:
            - key: .dockerconfigjson
              path: config.json
```






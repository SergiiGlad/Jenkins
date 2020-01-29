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

to create volumes secret

https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/#create-a-secret-in-the-cluster-that-holds-your-authorization-token
```
kubectl create secret docker-registry dockercred --docker-server='https://index.docker.io/v1/' --docker-username=sergeyglad --docker-password=<your-pword> --docker-email='gladseo@gmail.com'
```
pods/private-reg-pod.yaml 
apiVersion: v1
kind: Pod
metadata:
  name: private-reg
spec:
  containers:
  - name: private-reg-container
    image: <your-private-image>
  imagePullSecrets:
  - name: regcred



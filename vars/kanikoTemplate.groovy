def call(String podLabel, Closure code) { podTemplate(
    cloud: 'kubernetes',
    namespace: 'jenkins',
    label: podLabel,
    containers: [
      containerTemplate(
        name: 'golang',
        image: 'golang:1.13.0-alpine',
        ttyEnabled: true,
        command: 'cat'),
      containerTemplate(
        name: 'kaniko',
        image: 'gcr.io/kaniko-project/executor:debug',
        ttyEnabled: true,
        command: '/busybox/cat')],
    volumes: [
      secretVolume(mountPath: '.', secretName: 'dockercred', defaultMode: '420')
    ]) {    
code() }
}
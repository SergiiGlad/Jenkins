def call('docker', Closure code) { podTemplate(
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
        name: 'docker-dood',
        image: 'docker',
        ttyEnabled: true,
        command: 'cat'
        )
    ],
    volumes: [
      hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
    ]) {
code() }
}
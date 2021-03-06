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
        name: 'docker-dind',
        image: 'docker:stable-dind',
        ttyEnabled: true,
        privileged: true)
    ]) {
code() }
}
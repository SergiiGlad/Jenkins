def call(String namespace, String releaseName, String valuesFile, String imageTag) {
    withKubeConfig([credentialsId: 'kubeconfig']) {    
        sh """
                echo appVersion: $imageTag >> ${imageTag}/wikiChart/Chart.yaml
                helm upgrade --dry-run --debug \
                --install $releaseName \
                --namespace $namespace \
                --force \
                --wait \
                --values $valuesFile ${imageTag}/wikiChart \
                --set-string image.tag=$imageTag

                helm ls
        """
    }    
}
def deploy_to_branch(name, image, port){
    sh "docker pull ${image}"
    sh "docker rm -f ${name} || true"
    sh "docker run -d --name ${name} --expose ${port} -p ${port}:3000 ${image}"
}

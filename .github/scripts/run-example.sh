source ~/.bash_profile

echo "run SpringVulExample"
nohup java -jar target/SpringVulExample.jar &

echo "waiting for application run"
check_times=0
while [ $check_times -lt 30 ];
do
	echo 'checking api status'
	curl http://localhost:8080/index/name?username=owefsad
	if [ "$?" == "0" ];then
		break
	else
		let check_times++
		echo "api status is down, retry $check_times times after 10s..."
		sleep 10
	fi
done

echo "run api testing..."
curl http://localhost:8080/index/name?username=owefsad
curl http://localhost:8080/index/age?age=100
curl http://localhost:8080/cmd/runtime?cmd=whoami

sleep 30

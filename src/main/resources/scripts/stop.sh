ps -ef | grep demo | grep java | awk '{print $2}' | xargs kill
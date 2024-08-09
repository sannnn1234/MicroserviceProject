#Spring boot and Microservice 
<br>
First create new application in okta . Application type should be open id application. Authroization server has configuration related to access token and refresh token validity.

Go to security -> API -> default audience to modify access policies and timing.

To get refresh token , we need to ensure we have grant type refresh token for application and also scope is offline_access provided when retriving tokens. Refer application yaml of cloud gateway.

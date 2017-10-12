set projectLocation=C:\Users\SESA439753\git-local-repository\ssowindchillaccessrightsautomation\WindChillAccessRightsAutomation
cd %projectLocation%
set classpath=%projectLocation%\bin
set classpath=C:\Users\SESA439753\.m2\repository*;C:\Users\SESA439753\.m2\repository\org\testng\testng\6.11\testng-6.11.jar;%projectLocation%\lib\jcommander-1.7.jar
java org.testng.TestNG %projectLocation%\TestSuites\FolderTestSuite.xml
pause
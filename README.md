arquillian-test
===============

This project illustrates bug [ARQ-1842](https://issues.jboss.org/browse/ARQ-1842) in the Arquillian framework.
A remote JBOSS 7.1.1 Final application server is needed to execute test GeCarTest.

GeCarTest should fail with the "Started Execution of shouldFindAllCarsUsingJpqlQuery" message.
It currently fails with a NullPointerException, which is probably hiding another problem (see 
[ARQ-1842](https://issues.jboss.org/browse/ARQ-1842) for more details).
mvn install:install-file -Dfile=coverage-0.9.jar -DgroupId=edu.gmu.swe \
    -DartifactId=coverage -Dversion=0.9 -Dpackaging=jar

mvn install:install-file -Dfile=org.eclipse.uml2.types_1.0.0.v20120913-1441.jar -DgroupId=org.eclipse.uml2 \
    -DartifactId=types -Dversion=1.0.0.v20120913-1441 -Dpackaging=jar

mvn install:install-file -Dfile=org.eclipse.uml2.common_1.7.0.v20120913-1441.jar -DgroupId=org.eclipse.uml2 \
    -DartifactId=common -Dversion=1.7.0.v20120913-1441 -Dpackaging=jar
package de.fi.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void personenControllerMethod() {}

    @Pointcut("@within(de.fi.webapp.aspects.Dozent)")
    public void dozentMethodes(){}
}

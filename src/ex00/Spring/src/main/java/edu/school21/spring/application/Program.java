package edu.school21.spring.application;

import edu.school21.spring.printer.Printer;
import edu.school21.spring.printer.PrinterWithPrefixImpl;
import edu.school21.spring.renderer.Renderer;
import edu.school21.spring.renderer.RendererErrImpl;
import edu.school21.spring.preprocessor.PreProcessor;
import edu.school21.spring.preprocessor.PreProcessorToUpperImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        System.out.println("Standard method:");
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printerWithPrefix = new PrinterWithPrefixImpl(renderer);
        printerWithPrefix.setPrefix("Prefix");
        printerWithPrefix.print("Hello!");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println();
        System.out.println("Spring beans method:");
        Printer printer1 = context.getBean("printerWithPrefix", Printer.class);
        printer1.print("Hello!");
        Printer printer2 = context.getBean("printerWithDateTime", Printer.class);
        printer2.print("Hello!");
    }
}

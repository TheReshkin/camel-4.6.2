///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17+
//DEPS org.apache.camel:camel-bom:${camel.jbang.version:4.5.0}@pom
//DEPS org.apache.camel:camel-jbang-core:${camel.jbang.version:4.5.0}
//DEPS org.apache.camel.kamelets:camel-kamelets:${camel-kamelets.version:4.5.0}
//DEPS org.apache.camel:camel-decanter

package main;

import org.apache.camel.CamelContext;
import org.apache.camel.dsl.jbang.core.commands.CamelJBangMain;
import org.apache.camel.interceptor.DecanterInterceptStrategy;
import org.osgi.service.event.EventAdmin;

public class CamelJBang {

    public static void main(String... args) {
        // Создаем экземпляр CamelJBangMain
        CamelJBangMain main = new CamelJBangMain();

        // Доступ к CamelContext для настройки
        CamelContext camelContext = main.getCamelContext();

        // Настройка DecanterInterceptStrategy
        DecanterInterceptStrategy decanterCamelTracer = new DecanterInterceptStrategy();
        // Замените на ваш eventAdmin, если он доступен
        EventAdmin eventAdmin = null; 
        decanterCamelTracer.setDispatcher(eventAdmin);
        camelContext.addInterceptStrategy(decanterCamelTracer);

        // Запуск Camel с заданной стратегией
        main.run(args);
    }
}

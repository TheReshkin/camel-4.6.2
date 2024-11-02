///usr/bin/env jbang "$0" "$@" ; exit $?

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//JAVA 17+
//REPOS central=http://217.73.63.45:8081/repository/maven-karavan-proxy/, ri-karavan=https://repo.croc.ru:443/artifactory/RocInt
//DEPS org.apache.camel:camel-bom:${camel.jbang.version:4.6.2-SNAPSHOT}@pom
//DEPS org.apache.camel:camel-jbang-core:${camel.jbang.version:4.6.2-SNAPSHOT}
//DEPS org.apache.camel.kamelets:camel-kamelets:${camel-kamelets.version:4.6.0}

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
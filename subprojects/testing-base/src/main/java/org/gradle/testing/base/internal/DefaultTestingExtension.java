/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.testing.base.internal;

import org.gradle.api.Action;
import org.gradle.api.ExtensiblePolymorphicDomainObjectContainer;
import org.gradle.api.model.ObjectFactory;
import org.gradle.testing.base.TestSuite;
import org.gradle.testing.base.TestingExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public abstract class DefaultTestingExtension implements TestingExtension {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTestingExtension.class);

    private final ExtensiblePolymorphicDomainObjectContainer<TestSuite> suites;

    @Inject
    public DefaultTestingExtension() {
        this.suites = getObjectFactory().polymorphicDomainObjectContainer(TestSuite.class);
    }

    @Inject
    public abstract ObjectFactory getObjectFactory();

    @Override
    public ExtensiblePolymorphicDomainObjectContainer<TestSuite> getSuites() {
        return suites;
    }

    @Override
    public void configure(List<TestSuite> testSuites, Action<? super TestSuite> configureAction) {
        for (TestSuite testSuite : testSuites) {
            configureAction.execute(testSuite);
        }
    }
}

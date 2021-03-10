package ai.insyght.hireng;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ai.insyght.hireng");

        noClasses()
            .that()
                .resideInAnyPackage("ai.insyght.hireng.service..")
            .or()
                .resideInAnyPackage("ai.insyght.hireng.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ai.insyght.hireng.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}

package fr.manu.kanban;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fr.manu.kanban");

        noClasses()
            .that()
            .resideInAnyPackage("fr.manu.kanban.service..")
            .or()
            .resideInAnyPackage("fr.manu.kanban.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..fr.manu.kanban.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}

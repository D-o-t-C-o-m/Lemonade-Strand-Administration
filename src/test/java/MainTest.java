import domain.SupplierTest;
import repository.SupplierRepositoryTest;

public class MainTest {

public void runAllTests(){

	SupplierTest supplierTest = new SupplierTest();
	supplierTest.testAllDomain();

	SupplierRepositoryTest supplierRepository = new SupplierRepositoryTest();
	supplierRepository.testAllRepository();

	SupplierServiceTest supplierServiceTest = new SupplierServiceTest();
	supplierServiceTest.testAllService();

	System.out.println("All tests have run successfully");

}
}

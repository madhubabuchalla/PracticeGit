package test;

import com.schneider.windchillaccessrightsvalidation.catalogpart.CatalogPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.describedocument.DescribeDocumentAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.engineeredpart.EngineeredPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.genericlibraries.Config;
import com.schneider.windchillaccessrightsvalidation.managedcollection.ManagedCollectionAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.manufacturerpart.ManufacturerPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.mountingcontextpart.MountingContextPartAccessRightValidationSuite;
import com.schneider.windchillaccessrightsvalidation.objectlist.ObjectListPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.promotionrequest.PromotionRequestAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.rawmaterialpart.RawMaterialPartAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.referencedocument.ReferenceDocumentAccessRightsValidationSuite;
import com.schneider.windchillaccessrightsvalidation.treatmentpart.TreatmentPartAccessRightsValidationSuite;

public class DynamicObjectCreation {
	
	
	
	public static Object DynObjCreation(String objectName){
		
		Object obj = null;
		
		
		
		if(objectName.equalsIgnoreCase(Config.DESCRIBE_DOCUMENT)){
			obj = new DescribeDocumentAccessRightsValidationSuite(); 
			
				
			}else if(objectName.equalsIgnoreCase(Config.REFERENCE_DOCUMENT)){
				obj = new ReferenceDocumentAccessRightsValidationSuite();
				

			}else if(objectName.equalsIgnoreCase(Config.CATALOG_PART)){
				obj = new CatalogPartAccessRightsValidationSuite();
				
			}else if(objectName.equalsIgnoreCase(Config.ENGINEERED_PART)){
				obj = new EngineeredPartAccessRightsValidationSuite();
				

			}else if(objectName.equalsIgnoreCase(Config.MANAGED_COLLECTION)){
				obj = new ManagedCollectionAccessRightsValidationSuite();
				

			}else if(objectName.equalsIgnoreCase(Config.MANUFACTURER_PART)){
				obj = new ManufacturerPartAccessRightsValidationSuite();
				

			}else if(objectName.equalsIgnoreCase(Config.MOUNTING_CONTEXT_PART)){
				obj = new MountingContextPartAccessRightValidationSuite();
				
			}else if(objectName.equalsIgnoreCase(Config.OBJECT_LIST_PART)){
				obj = new ObjectListPartAccessRightsValidationSuite();
			

			}else if(objectName.equalsIgnoreCase(Config.RAWMATERIAL_PART)){
				obj = new RawMaterialPartAccessRightsValidationSuite();
			

			}else if(objectName.equalsIgnoreCase(Config.TREATMENT_PART)){
				obj = new TreatmentPartAccessRightsValidationSuite();
				

			}else if(objectName.equalsIgnoreCase(Config.PROMOTION_REQUEST)){
				
				obj = new PromotionRequestAccessRightsValidationSuite();
				
				
			}
	
		return obj;
		
	}
	
	public static void main(String arg[]){
		
	//	Object obj = DynObjCreation("Describe Document");
		
		
	}
	

}

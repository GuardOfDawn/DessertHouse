package dessert.action.headAttendant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Product;
import dessert.service.productOperation.ProductOpService;
import dessert.utility.IDProducer;

@Controller
public class ProductAddAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductOpService productManage;
	
	public String execute(){
		if(session.get("userId")!=null){
			Product newProduct = new Product();
			newProduct.setProductId(IDProducer.getInstance().produceProductId());
			newProduct.setProductName(request.getParameter("productName"));
			newProduct.setProductType(request.getParameter("productType"));
			String imageName = (request.getParameter("imagePath")==null)?"default.jpg":request.getParameter("imagePath");
			String imagePath = "/upload/" + imageName;
			newProduct.setImagePath(imagePath);
			productManage.addProduct(newProduct);
			request.setAttribute("newProduct", newProduct);
			return SUCCESS;
			
//			String uploadPath = request.getSession().getServletContext().getRealPath("/")+"upload/images/";
//			String tempPath = request.getSession().getServletContext().getRealPath("/")+"upload/temp/";
//			if(!new File(uploadPath).isDirectory())
//				new File(uploadPath).mkdirs();
//			if(!new File(tempPath).isDirectory())
//				new File(tempPath).mkdirs();
//			try {
//				DiskFileUpload fu = new DiskFileUpload();
//				fu.setSizeMax(4194304);
//				fu.setSizeThreshold(4096);
//				fu.setRepositoryPath(tempPath);
//			   	List fileItems = fu.parseRequest(request);
//			   	Iterator i = fileItems.iterator();
//			   	while(i.hasNext()) {
//			   		FileItem file = (FileItem)i.next();
//			   		String sourcefileName = file.getName();
//			   		if(sourcefileName!=null){
//			   			String[] parts = sourcefileName.split("\\");
//			   			File f1=new File(uploadPath+ parts[parts.length-1]);
//			   			file.write(f1);
//			   			newProduct.setImagePath(uploadPath+ parts[parts.length-1]);
//			   		}
//			   	}
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}

# MediaStoreAPIImages
Through This Repo you can easily get all Images of storage or Images folder wise also

Write below this code to feth all images of storage
	here argument context is activity context
	
	ArrayList<Uri> allImages = loadAllImages(context);


Wright below code to fetch all Folder names which contains images
	here argument context is activity context
	
	ArrayList<String> folderName = loadImageFolderName(context);

Below code return all images of given folderName
	here arument context is Ativity context and folderName is name of folder
	Here we have already have fetch folder name wwhih contains images using loadImageFolderName(contxt); Method	
	
	
	ArrayList<Uri> folderImages = loadImagesFromFolder(folderName,context);

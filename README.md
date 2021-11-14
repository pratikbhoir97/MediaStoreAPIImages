# MediaStoreAPIImages
Through This Repo you can easily get all Images of storage or Images folder wise also

> Step 1: Add it in your root build.gradle at the end of repositories:


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
>Step 2: Add the dependency


	dependencies {
	        implementation 'com.github.pratikbhoir97:MediaStoreAPIImages:-SNAPSHOT'
	}
	
>step 3: HOW TO USE

Initialise object of class
	
	MediaStoreAPIUitility mediaStoreAPIUitility = new MediaStoreAPIUitility();
	
Write below this code to fetch all images of storage
	here argument context is activity context
	
	//To load all Images
        ArrayList<Uri> allImages = mediaStoreAPIUitility.loadAllImages(this);


Wright below code to fetch all Folder names which contains images
	here argument context is activity context
	
	//To load Images Foler wise
        //First load all Folder names which contain Images
        ArrayList<String> bucketName = mediaStoreAPIUitility.loadImageFolderName(this);

Below code return all images of given folderName
	here arument context is Ativity context and folderName is name of folder
	Here we have already have fetch folder name wwhih contains images using loadImageFolderName(contxt); Method	
	
	
	//Pass any folder name Like Im passing here name of 0'th position
        ArrayList<Uri> imagesByFolder = mediaStoreAPIUitility.loadImagesFromFolder(bucketName.get(0),this);

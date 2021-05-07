$(document).ready(function(){
 $('.sortable').sortable();
   
});

//DropzoneJS snippet - js
//$.getScript('https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/min/dropzone.min.js',function(){
$.getScript('../bower_components/dropzone/dropzone.min.js',function(){
  
	Dropzone.prototype.defaultOptions.dictDefaultMessage = "Drop files here to upload";
	Dropzone.prototype.defaultOptions.dictFallbackMessage = "Your browser does not support drag'n'drop file uploads.";
	Dropzone.prototype.defaultOptions.dictFallbackText = "Please use the fallback form below to upload your files like in the olden days.";
	Dropzone.prototype.defaultOptions.dictFileTooBig = "File is too big ({{filesize}}MiB). Max filesize: {{maxFilesize}}MiB.";
	Dropzone.prototype.defaultOptions.dictInvalidFileType = "You can't upload files of this type.";
	Dropzone.prototype.defaultOptions.dictResponseError = "Server responded with {{statusCode}} code.";
	Dropzone.prototype.defaultOptions.dictCancelUpload = "Cancel upload";
	Dropzone.prototype.defaultOptions.dictCancelUploadConfirmation = "Are you sure you want to cancel this upload?";
	Dropzone.prototype.defaultOptions.dictRemoveFile = "Remove file";
	Dropzone.prototype.defaultOptions.dictMaxFilesExceeded = "You can not upload any more files.";


  // instantiate the uploader
  $('#file-dropzone').dropzone({ 
    url: "sendmessagetochannel?action=uploadfile",
    maxFiles: 3, //number of images a user should upload at an instance
    maxFilesize: 10, //mb- Image files not above this size
    //acceptedFiles: 'image/jpeg, image/jpg, image/png',
    acceptedFiles: ".png,.jpg,.jpeg,.gif,.pdf,.doc,.docx", //allowed file types, .pdf or anyother would throw error
    thumbnailWidth: 100,
    thumbnailHeight: 100,
    autoProcessQueue: true, // Prevents Dropzone from uploading dropped files immediately
    paramName: "uploadfile",
    maxThumbnailFilesize: 99999,
    /*
    dictRemoveFile: 'Remove Logo',
    accept: function(file, done) {
        console.log("Uploaded");
        done();
    },
    */
    autoQueue: true,
    //addRemoveLinks: true, // add a remove link underneath each image to 

    
    previewsContainer: '.visualizacao', 
    previewTemplate : $('.preview').html(),
    init: function() {
    	      
      this.on('completemultiple', function(file, json) {
    	  //console.log('onCompletemultiple',file)
    	  $('.sortable').sortable('enable');       
      });
      this.on('success', function(file, json) {
    	  //console.log('onSuccess',file)
    	  //alert('test');
    	  
      });
      
      
      this.on('addedfile', function(file) {
    	  //console.log('onAddedFile',file)
    	  
    	  // --------
          var ext = file.name.split('.').pop();

          if (ext == "pdf") {
        	  //console.log('onAddedFile: pdf file detected')
        	  $(file.previewElement).find(".data-dz-thumbnail").attr("src", "../common/images/thumbnails/pdf.png");
          } else if (ext.indexOf("doc") != -1) {
              $(file.previewElement).find(".data-dz-thumbnail").attr("src", "../common/images/thumbnails/doc.png");
          } else if (ext.indexOf("xls") != -1) {
              $(file.previewElement).find(".data-dz-thumbnail").attr("src", "../common/images/thumbnails/xls.png");
          } else if (ext.indexOf("xlsx") != -1) {
              $(file.previewElement).find(".data-dz-thumbnail").attr("src", "../common/images/thumbnails/xls.png");
          } else if ((ext.indexOf("png") != -1) || (ext.indexOf("jpg") != -1) || (ext.indexOf("jpeg") != -1) || (ext.indexOf("gif") != -1)) {
              // Do not add a default image; instead image preview is displayed
          } else {
        	  $(file.previewElement).find(".data-dz-thumbnail").attr("src", "../common/images/thumbnails/file.png");
          }
          // --------
          
    	  // Create the remove button
          var removeButton = Dropzone.createElement("<button><span>✘</span></button>");
          

          // Capture the Dropzone instance as closure.
          var _this = this;

          // Listen to the click event
          removeButton.addEventListener("click", function(e) {
            // Make sure the button click doesn't submit the form:
            e.preventDefault();
            e.stopPropagation();

            // Remove the file preview.
            _this.removeFile(file);
            // If you want to the delete the file on the server as well,
            // you can do the AJAX request here.
          });

          // Add the button to the file preview element.
          file.previewElement.appendChild(removeButton);
          
          
    	  
      });
      
      this.on('drop', function(file) {
        console.log('onDrop',file)
      }); 
    }
  });
});
$(document).ready(function() {});
								$( document ).ready(function() {
									var $uploadCrop;

									function formatFileSize(bytes, si) {
									    var thresh = si ? 1000 : 1024;
									    if(Math.abs(bytes) < thresh) {
									        return bytes + ' B';
									    }
									    var units = si
									        ? ['kB','MB','GB','TB','PB','EB','ZB','YB']
									        : ['KiB','MiB','GiB','TiB','PiB','EiB','ZiB','YiB'];
									    var u = -1;
									    do {
									        bytes /= thresh;
									        ++u;
									    } while(Math.abs(bytes) >= thresh && u < units.length - 1);
									    return bytes.toFixed(1)+' '+units[u];
									}
									
									$('#close').click(
											function() {
												$('#overlayuploadcontainer').hide('slow', 
													 function() {
														  $('#overlayupload').fadeOut();          
													 }    
												);
											}
										); 
									
									function readFile(input) {
										
										if (input.files && input.files[0].size > 500000) { // max < 500kb file
											
											//alert('File too large. Max 500 KB allowed, but currently: '+formatFileSize(input.files[0].size,true));
											
											//var overlaysendmsghtmlerror = "<center><table width='100%' class='overlayupload' height='100%'><tr><td width=20><img src='../common/images/error-48x48.png' height='42'></td><td align='center'><span>File size too large</span></td></tr></table></center>";
											$('#overlayupload').show('fast', 
								                    function() {
								                        $('#overlayuploadcontainer').fadeIn('fast');
								                        $('#changeText').html(overlaysendmsghtmlerror);
								                    }
								            );											
											return;
										}
										
										if (input.files && input.files[0]) {
											var reader = new FileReader();          
											reader.onload = function (e) {
												$uploadCrop.croppie('bind', {
													url: e.target.result
												});
												$('.upload-demo').addClass('ready');
											}           
											reader.readAsDataURL(input.files[0]);
										}
									}

									$uploadCrop = $('#upload-demo').croppie({
										viewport: {
											width: 150,
											height: 150,
											type: 'circle'
										},
										boundary: {
											width: 200,
											height: 200
										},
										  showZoomer: true,
										  enableOrientation: true
									});
									
									$('.upload-rotate').on('click', function(ev) {
										$uploadCrop.croppie('rotate', parseInt($(this).data('deg')));
									});

									$('#upload').on('change', function () { readFile(this); });
									
									$('.upload-result').on('click', function (ev) {
										$uploadCrop.croppie('result', {
											type: 'canvas',
											size: 'original'
										}).then(function (resp) {
											$('#imagebase64').val(resp);
											$('#form').submit();
										});
									});

								});
					<div class="box" style="border-top: 0px solid #000;">			          	
			            <div class="box-body table-responsive no-padding">
							<iframe style="height:500px;width:100%;border:2px solid lightgray;" src="${bl_smallview_webui_url}"></iframe>
			            </div>
			            
			         </div>
         			<!-- /.box -->
         
         			<div class="media-body">
                    	<div class="clearfix">
                    		<span><i class="fa fa-microchip"></i> Plugin Name: <span style="color: #006400; font-family: Courier New,Courier,Lucida Sans Typewriter,Lucida Typewriter,monospace;">${plugin_name}</span>
    							
    						<p class="pull-right">
    							<button title="Navigate to WebUI's startpage" type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('webui-large-${number}')[0]; ifr.src='${bl_webui_url}';"><i class="fa fa-fw fa-home"></i></button>
						        <button title="Refresh actual loaded WebUI page" type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('webui-large-${number}')[0]; ifr.src=ifr.src;"><i class="fa fa-fw fa-refresh"></i></button>
						                
    							<button title="Start developer console to call functions in a native way" type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-bb-console-${number}"><i class="fa fa-fw fa-code"></i> Developer Console</button>
    							 							
                           		<button title="Start a conversation with the business logic"  type="button" id="runTesterBtn" class="btn btn-default" title="Run" data-toggle="modal" data-target="#modal-runTester" ><i class="fa fa-fw fa-play"></i> Start conversation</button>
 
                           		<button title="Show the WebUI interface of the business logic" type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-bb-webui-${number}"><i class="fa fa-fw fa-desktop"></i> Show Web-Interface</button>
                           		<a target="_new" href="${button_bl_webui_external_webui_url}"><button title="Show Web-Interface in fullscreen" type="button" class="btn btn-default" data-target="#modal-bb-webui-${number}"><i class="fa fa-fw fa-arrows-alt"></i></button></a>
                           		
                           		<!-- modal-runTester -->
				            	<div class="modal fade" id="modal-runTester">
						          <div class="modal-dialog" style="width: 900px; height: 600px">
						            <div class="modal-content">
						              <div class="modal-header" style="background-color: #f6f6f6; border-bottom-color: silver;">
						                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						                  <span aria-hidden="true">&times;</span></button>
						                <h4 class="modal-title"><i class="fa fa-fw fa-play"></i> Run</h4>
						              </div>
						              <div class="modal-body direct-chat direct-chat-primary">
						                  <!-- Conversations are loaded here -->
							              <div id="chatMessages" class="direct-chat-messages">
							                <!-- Message. Default to the left --><div class='direct-chat-msg'><div class='direct-chat-info clearfix'><span class='direct-chat-name pull-left'>Business Logic</span><span class='direct-chat-timestamp pull-right'></span></div><!-- /.direct-chat-info --><img class='direct-chat-img' style='background-color: #d5d1d1;' src='common/images/citunius-48x48.png' alt='Message Bot Image'><!-- /.direct-chat-img --><div class='direct-chat-text'>You can start the conversation now...</div><!-- /.direct-chat-text --></div><!-- /.direct-chat-msg -->
							            		    	
							              </div>
							              <!--/.direct-chat-messages-->
							
							              <!-- /.direct-chat-pane -->
						              </div><!-- ./model-body -->
						              <div class="modal-footer" style="background-color: #f6f6f6; border-bottom-color: silver;">
							              <form name="form" action="#" method="post">
								                <div class="input-group">
								                	<input type="text" id="chatMessage" name="chatMessage" placeholder="Type Message ..." class="form-control">
								                    <span class="input-group-btn">
								                    	<button id="submitBtn" type="submit" class="btn btn-primary btn-flat">Send</button>
								                    </span>
								                </div>
							              </form>
							              <script>
							              function formatAMPM() {
							            	  	var monthShortNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
							            		var date = new Date();
												
							            	  	var hours = date.getHours();
							            	  	var minutes = date.getMinutes();
							            	  	var ampm = hours >= 12 ? 'pm' : 'am';
							            	  	hours = hours % 12;
							            	  	hours = hours ? hours : 12; // the hour '0' should be '12'
							            	  	minutes = minutes < 10 ? '0'+minutes : minutes;
							            	  	var strTime = hours + ':' + minutes + ' ' + ampm;
							            	  	var time = date.getDate() + " " + monthShortNames[date.getMonth()] + " " + strTime; // 23 Jan 2:05 pm												
							            	  	return time;
							              }
							              
							              $('#modal-runTester').on('shown.bs.modal', function () {
							            	  $('#chatMessage').focus();
							              })  
							            	
							              $("#submitBtn").click(function(e){        
							            	  e.preventDefault();
							            	  console.log("Submit chat message...");								            	  
							            	  	// Add chat message
							            		var userMsg = $('#chatMessage').val();
							            		$('#chatMessages').append("<!-- Message to the right --><div class='direct-chat-msg right'><div class='direct-chat-info clearfix'><span class='direct-chat-name pull-right'>User</span><span class='direct-chat-timestamp pull-left'>" + formatAMPM() + "</span></div><!-- /.direct-chat-info --><img class='direct-chat-img' style='background-color: #d5d1d1;' src='common/images/user-48x48.png' alt='Message User Image'><!-- /.direct-chat-img --><div class='direct-chat-text'>" + userMsg + "</div><!-- /.direct-chat-text --></div><!-- ./Message to the right -->");
							            		$('#chatMessage').val("");
							            		
							            		$.ajax({ 
							            		    type: 'GET', 
							            		    url: '${bl_handleIncomingMessage_url}', 
							            		    data: { consoleMessage: userMsg }, 
							            		    dataType: 'json',
							            		    success: function (data) { 							            		        
							            		    	$('#chatMessages').append("<!-- Message. Default to the left --><div class='direct-chat-msg'><div class='direct-chat-info clearfix'><span class='direct-chat-name pull-left'>Business Logic</span><span class='direct-chat-timestamp pull-right'>" + formatAMPM() + "</span></div><!-- /.direct-chat-info --><img class='direct-chat-img' style='background-color: #d5d1d1;' src='common/images/citunius-48x48.png' alt='Message Bot Image'><!-- /.direct-chat-img --><div class='direct-chat-text'>" + data.message + "</div><!-- /.direct-chat-text --></div><!-- /.direct-chat-msg -->");
							            		    	// Scroll to bottom
							            		    	$("#chatMessages").animate({ scrollTop: $('#chatMessages').prop("scrollHeight")}, 1500);
							            		    }
							            		});
							            		// Scroll to bottom
							            		$("#chatMessages").animate({ scrollTop: $('#chatMessages').prop("scrollHeight")}, 1500);
						            	    });
							              </script>
						              </div>
						            </div>
						            <!-- /.modal-content -->						            
						          </div>
						          <!-- /.modal-dialog -->
						        </div>
						        <!-- /.modal-runTester -->
						        
								<!-- modal - bb-webui --> 
			                    <div class="modal fade" id="modal-bb-webui-${number}">
						          <div class="modal-dialog modal-lg">
						            <div class="modal-content">
						              <div class="modal-header" style="background-color: #f6f6f6; border-bottom-color: silver;">
						                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						                  <span aria-hidden="true">&times;</span></button>
						                <h4 class="modal-title"><i class="fa fa-fw fa-desktop"></i> Business Logic - Web-Interface</h4>
						              </div>
						              <div class="modal-body">
						                <iframe id="webui-large-${number}" name="webui-large-${number}" style="height:500px;width:100%;border:2px solid lightgray;" src="${bl_webui_url}"></iframe>						                
						              </div>
						              <div class="modal-footer" style="background-color: #f6f6f6; border-bottom-color: silver;">						              	
										<button type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('webui-large-${number}')[0]; ifr.src='${bl_webui_url}';"><i class="fa fa-fw fa-home"></i></button>
						              	<button type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('webui-large-${number}')[0]; ifr.src=ifr.src;"><i class="fa fa-fw fa-refresh"></i></button>
						                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-fw fa-remove"></i> Close</button>
						              </div>
						            </div>
						            <!-- /.modal-content -->
						          </div>
						          <!-- /.modal-dialog -->
						        </div>
						        <!-- /.modal - bb-webui -->
						        
						        <!-- modal - bb-console --> 
			                    <div class="modal fade" id="modal-bb-console-${number}">
						          <div class="modal-dialog modal-lg">
						            <div class="modal-content">
						              <div class="modal-header" style="background-color: #f6f6f6; border-bottom-color: silver;">
						                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						                  <span aria-hidden="true">&times;</span></button>
						                <h4 class="modal-title"><i class="fa fa-code"></i> Business Logic - Developer Console</h4>
						              </div>
						              <div class="modal-body">
						                <!-- Modified version -->
						                <iframe id="console-${number}" name="console-${number}" style="height:500px;width:100%;border:2px solid lightgray;" src="${bl_webui_url}"></iframe>						                
						              </div>
						              <div class="modal-footer" style="background-color: #f6f6f6; border-bottom-color: silver;">	

								        <form name="form" id="form" role="form" method="POST" onsubmit="return false">        
								                <div class="input-group">
									                <div class="input-group-btn dropdown-action">
									                  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Action
									                    <span class="fa fa-caret-down"></span></button>
									                  <ul id="actionMenu" class="dropdown-menu">
									                    <li value="sendMessage"><a href="#">sendMessage()</a></li>
									                    <li value="handleIncomingMessage"><a href="#">handleIncomingMessage()</a></li>
									                    <li value="handleIncomingCallback"><a href="#">handleIncomingCallback()</a></li>									                    
									                  </ul>
									                </div>
									                <!-- /btn-group -->
									                <input type="text" id="consoleMessage" name="consoleMessage" placeholder="Type Message ..." class="form-control">
									                <span class="input-group-btn">
								                    	<button id="submitConsoleBtn" type="submit" class="btn btn-primary btn-flat">Send</button>
								                    </span>
									            </div>
								                
								                
							              </form>
							              <script>	
							              var selectedAction;
							              $(".dropdown-menu li a").click(function(){
							            	  $(this).parents(".dropdown-action").find('.btn').html($(this).text() + ' <span class="caret"></span>');
							            	  $(this).parents(".dropdown-action").find('.btn').val($(this).data('value'));
							            	  selectedAction = $(this).closest('li').attr("value");
							            	  //console.log("Selected action: "+selectedAction);
							            	});
							              
							              $('#modal-bb-console-${number}').on('shown.bs.modal', function () {
							            	  $('#consoleMessage').focus();
							              })  
							            	
							              $("#form").submit(function(e) {
							            	  	// Prevent form submit
							            	  	e.preventDefault();
							            	  	console.log("Submit console message...");								            	  
							            	  	console.log('[FormData] Selected action value: ' + selectedAction);
							            	  	
							            	    // Add chat message
							            		var userMsg = $('#consoleMessage').val();
							            		console.log('[FormData] Selected userMsg value: ' + userMsg);
							            		$('#console-${number}').append("<!-- Message to the right --><div class='direct-chat-msg right'><div class='direct-chat-info clearfix'><span class='direct-chat-name pull-right'>User</span><span class='direct-chat-timestamp pull-left'>" + formatAMPM() + "</span></div><!-- /.direct-chat-info --><img class='direct-chat-img' style='background-color: #d5d1d1;' src='common/images/user-48x48.png' alt='Message User Image'><!-- /.direct-chat-img --><div class='direct-chat-text'>" + userMsg + "</div><!-- /.direct-chat-text --></div><!-- ./Message to the right -->");
							            		$('#consoleMessage').val("");
							            		
							            		$.ajax({ 
							            		    type: 'GET', 
							            		    url: '${bl_action_url}', 
							            		    data: { consoleMessage: userMsg,
							            		    		action: selectedAction,
							            		    }, 
							            		    dataType: 'text',
							            		    async: true,
							            		    statusCode: {
							            		        404: function (response) {
							            		            alert(404);
							            		        },
							            		        200: function (response) {
							            		            //alert(response);
							            		        }
							            		    },
							            		    error: function (jqXHR, status, errorThrown) {
							            		        alert('error');
							            		    },
							            		    success: function (data) { 							            		        
							            		    	console.log("Response: "+data.message)
							            		    	var jsonPretty;
							            		    	if (data) {
							            		    		var jsonObj = JSON.parse(data);
															jsonPretty = JSON.stringify(jsonObj, null, '\t');
							            		    	} else {
							            		    		jsonPretty = "Response is empty. No data.";
							            		    	}
							            		    	$('#console-${number}').contents().find('html').html("<pre><b>Response:</b>\n\n"+jsonPretty+"</pre>");							            		    	
							            		    }
							            		});
							            		// Scroll to bottom
							            		$("#console-${number}").animate({ scrollTop: $('#console-${number}').prop("scrollHeight")}, 1500);
						            	    });
							              </script>
						              	<hr>
						              	<button title="isLicenseRequired()" type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('console-${number}')[0]; ifr.src='${button_bl_isLicenseRequired_url}';"><i class="fa fa-fw fa-certificate"></i></button>
						              	<button title="isBotBuilderModelSupported()" type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('console-${number}')[0]; ifr.src='${button_bl_isBotBuilderModelSupported_url}';"><i class="fa fa-fw fa-cube"></i></button>
						              	<button title="isWebUISupported()" type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('console-${number}')[0]; ifr.src='${button_bl_isWebUISupported_url}';"><i class="fa fa-fw fa-desktop"></i></button>
						              	&nbsp;|&nbsp;
						              	<button type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('console-${number}')[0]; ifr.src='${button_bl_prepare_url}';"><i class="fa fa-fw fa-code"></i> Prepare()</button>
						              	<button type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('console-${number}')[0]; ifr.src='${button_bl_getbotbuilderfunctions_url}';"><i class="fa fa-fw fa-puzzle-piece"></i> getBotBuilderPluginFunctions()</button>
						              	&nbsp;|&nbsp;				              	
										<button type="button" class="btn btn-default" onclick="var ifr=document.getElementsByName('console-${number}')[0]; ifr.src=ifr.src;"><i class="fa fa-fw fa-refresh"></i></button>
						                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-fw fa-remove"></i> Close</button>
						              </div>
						            </div>
						            <!-- /.modal-content -->
						          </div>
						          <!-- /.modal-dialog -->
						        </div>
						        <!-- /.modal - bb-console -->
                           		
							</p>
                        </div>
                    </div>
                    
                   
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate, private" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="-1" />

    <title>Developer UI</title>

	<link rel="apple-touch-icon" sizes="57x57" href="common/images/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="common/images/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="common/images/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="common/images/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="common/images/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="common/images/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="common/images/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="common/images/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="common/images/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="common/images/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="common/images/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="common/images/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="common/images/favicon/favicon-16x16.png">
	<link rel="manifest" href="/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="../common/images/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">

	<link rel="shortcut icon" type="image/x-icon" href="common/images/favicon/favicon.ico">
	<link id="favicon" rel="SHORTCUT ICON" href="common/images/favicon/favicon.ico">

	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.7 -->
	<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
	<!-- AdminLTE Skins. Choose a skin from the css/skins
	     folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
	<!-- Morris chart -->
	<!-- <link rel="stylesheet" href="bower_components/morris.js/morris.css">  -->
	<!-- jvectormap -->
	<link rel="stylesheet" href="bower_components/jvectormap/jquery-jvectormap.css">
	<!-- Date Picker -->
	<link rel="stylesheet" href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
	<!-- Daterange picker -->
	<link rel="stylesheet" href="bower_components/bootstrap-daterangepicker/daterangepicker.css">
	<!-- bootstrap wysihtml5 - text editor -->
	<link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	<!-- Google Font -->
	<link rel="stylesheet" href="css/fonts.css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

	<!-- Bootstrap-Switch CSS -->
	<link href="bower_components/bootstrap-toggle/css/bootstrap-toggle.min.css" rel="stylesheet">
    
    <!-- BBP custom styles -->
	<link href="custom/bbp-styles/bbp.css" rel="stylesheet">
    
    <!-- JS Scripts -->
    
    <!-- jQuery 3 -->
	<script src="bower_components/jquery/dist/jquery.min.js"></script>
	
	<!-- jQuery UI 1.11.4 --> 
	<script src="bower_components/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
	  $.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- jQuery Validation 1.17.0 --> 
	<script src="bower_components/jquery-validation/jquery.validate.min.js"></script>
	
	<!-- Bootstrap 3.3.7 -->
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	    
    <!-- Sparkline -->
	<script src="bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- jvectormap -->
	<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script src="bower_components/moment/min/moment.min.js"></script>
	<script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>
	
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!-- <script src="dist/js/pages/dashboard.js"></script>  -->
	<!-- AdminLTE for demo purposes -->
	<!-- <script src="dist/js/demo.js"></script>  -->
	
	
	<!-- Bootstrap-Toggle Button JavaScript -->
	<script src="bower_components/bootstrap-toggle/js/bootstrap-toggle.min.js"></script>
	
	<!-- Bootstrap Multiple-Select -->
	<link href="bower_components/jquery-multiple-select/dist/css/bootstrap-multiselect.css" rel="stylesheet">
	<script src="bower_components/jquery-multiple-select/dist/js/bootstrap-multiselect.js"></script>
	
	<!-- Bootstrap Input Spinner -->
	<script src="bower_components/bootstrap-spinner/dist/jquery.spinner.min.js"></script>
	<link href="bower_components/bootstrap-spinner/dist/bootstrap-spinner.css" rel="stylesheet">
    
    	<!-- DataTables CSS -->
    <link href="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="bower_components/datatables-responsive/css/responsive.dataTables.min.css" rel="stylesheet">
    
    
        
	
	<!-- Facebook-style tooltip plugin for jQuery -->
	<script type="text/javascript" src="custom/tipsy/jquery.tipsy.js"></script>
	<link rel="stylesheet" href="custom/tipsy/tipsy.css" type="text/css" />
	
	<script type='text/javascript'>
	$(document).ready(function() {
		$("#pop1, #pop2, #pop3, #pop4").tipsy({gravity: 's'}).mouseenter(function(){
	    	var top=$(this).find('h2').offset().top - 35, left=$(this).find('h2').offset().left - 10;
	    	$('.tipsy').css({'top': top + 'px',	'left': left + 'px'});
		});
	});
	</script>
	
</head>

<body class="hold-transition skin-blue layout-top-nav">

	<div class="wrapper">
		
		<!-- {navigation} -->
		
		<!-- Content Wrapper. Contains page content -->				
		  <div class="content-wrapper">
		    <!-- Content Header (Page header) -->
		    <section class="content-header">
		      <h1>
		        <i class="fa fa-fw fa-microchip"></i> Developer UI - Business Logic
		        <small>Developer UI View for the Business Logic</small>
		      </h1>
		      <ol class="breadcrumb">
		        <i class="fa fa-fw fa-microchip"></i> Developer UI > Business Logic
		      </ol>
		    </section>
		
		    <!-- Main content -->
		    <section class="content">
		      <!-- Main row -->
		      <div class="row">
		      	
		        <!-- Chatbot logic panels -->       
			        <section class="content">
			        	<div class="row">
			        		<!-- business logic -->
							<div class="col-md-12">
					          <div class="nav-tabs-custom" style="">
					            <ul class="nav nav-tabs" style="background-color: #f6f6f6; border-bottom-color: silver;">
					              <li class="active"><a href="#webui-${number}" data-toggle="tab" aria-expanded="true"><i class="fa fa-object-group"></i> WebUI</a></li>
					              <li><a href="#settings-${number}" data-toggle="tab"><i class="fa fa-gears"></i> Settings</a></li>					              
					              <li><a href="#about-${number}" data-toggle="tab"><i class="fa fa-info-circle"></i> About</a></li>					              					              
					            </ul>
					            <div class="tab-content">
					            	<div class="tab-pane active" id="webui-${number}">
					                ${webui}
					              </div>
					              <div class="tab-pane" id="settings-${number}">
					                ${settings}
					              </div>					              
					              <div class="tab-pane" id="about-${number}">
					                ${about}
					              </div>
					            </div>
					            <!-- /.tab-content -->
					          </div>
					          <!-- /.nav-tabs-custom -->
					        </div>
					        <!-- /.business logic -->
			        	</div>
			        	<!-- /.row -->
			        </section>
			    <!--  /.Chatbot logic panel -->
		        
		      </div>
		      <!-- /.row (main row) -->
		
		    </section>
		    <!-- /.content -->
		  </div>
		<!-- /.content-wrapper -->
		
		<!-- {sidebar}  -->
	</div>
	<!-- ./wrapper -->


	
    <!-- DataTables JavaScript -->
    <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="bower_components/datatables-responsive/js/dataTables.responsive.min.js"></script>
    
    <!-- DataTables Plugin: searchHighlight -->
    <link href="bower_components/datatables-plugins/features/searchHighlight/dataTables.searchHighlight.css" rel="stylesheet">
    <script src="bower_components/datatables-plugins/features/searchHighlight/dataTables.searchHighlight.min.js"></script>
	
	<!-- DataTables: Tables definition -->
    <script>
    $(document).ready(function() {
    	jQuery.extend({
    	    highlight: function (node, re, nodeName, className) {
    	        if (node.nodeType === 3) {
    	            var match = node.data.match(re);
    	            if (match) {
    	                var highlight = document.createElement(nodeName || 'span');
    	                highlight.className = className || 'highlight-searchresult';
    	                var wordNode = node.splitText(match.index);
    	                wordNode.splitText(match[0].length);
    	                var wordClone = wordNode.cloneNode(true);
    	                highlight.appendChild(wordClone);
    	                wordNode.parentNode.replaceChild(highlight, wordNode);
    	                return 1; //skip added node in parent
    	            }
    	        } else if ((node.nodeType === 1 && node.childNodes) && // only element nodes that have children
    	                !/(script|style)/i.test(node.tagName) && // ignore script and style nodes
    	                !(node.tagName === nodeName.toUpperCase() && node.className === className)) { // skip if already highlighted
    	            for (var i = 0; i < node.childNodes.length; i++) {
    	                i += jQuery.highlight(node.childNodes[i], re, nodeName, className);
    	            }
    	        }
    	        return 0;
    	    }
    	});

    	jQuery.fn.unhighlight = function (options) {
    	    var settings = { className: 'highlight-searchresult', element: 'span' };
    	    jQuery.extend(settings, options);

    	    return this.find(settings.element + "." + settings.className).each(function () {
    	        var parent = this.parentNode;
    	        parent.replaceChild(this.firstChild, this);
    	        parent.normalize();
    	    }).end();
    	};

    	jQuery.fn.highlight = function (words, options) {
    	    var settings = { className: 'highlight-searchresult', element: 'span', caseSensitive: false, wordsOnly: false };
    	    jQuery.extend(settings, options);
    	    
    	    if (words.constructor === String) {
    	        words = [words];
    	    }
    	    words = jQuery.grep(words, function(word, i){
    	      return word != '';
    	    });
    	    words = jQuery.map(words, function(word, i) {
    	      return word.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
    	    });
    	    if (words.length == 0) { return this; };

    	    var flag = settings.caseSensitive ? "" : "i";
    	    var pattern = "(" + words.join("|") + ")";
    	    if (settings.wordsOnly) {
    	        pattern = "\\b" + pattern + "\\b";
    	    }
    	    var re = new RegExp(pattern, flag);
    	    
    	    return this.each(function () {
    	        jQuery.highlight(this, re, settings.element, settings.className);
    	    });
    	};
    	
    	var myTable = $('#dataTables-example').DataTable({
                responsive: true,
                searchHighlight: true,
                "order": [[ 0, "desc" ]],
                "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
        });
    	
    	// Select all datatable starting with 'dataTables-widget'
    	//var myTable = $('#dataTables-widget').DataTable({
    	var myTableWidget = $("[id^='dataTables-widget']").DataTable({
            responsive: true,
            searchHighlight: true,
            "order": [[ 0, "desc" ]],
            searching: false, 
            paging: true,
            "ordering": false,
            "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
    	});
    	
    	var myTableWidgetStatic = $("[id^='dataTables-botinstance-tab-botaccount-widget']").DataTable({
            responsive: false,
            searchHighlight: false,
            "order": [[ 0, "desc" ]],
            searching: false, 
            paging: false,
            "ordering": false,
            "bInfo": false,
            "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
    	});
    	
    	var myTableWidgetStaticMedia = $("[id^='dataTables-botinstance-tab-media-widget']").DataTable({
            responsive: false,
            searchHighlight: false,
            "order": [[ 0, "desc" ]],
            searching: false, 
            paging: false,
            "ordering": false,
            "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
    	});
        
        $('#dataTables-licenses').DataTable({
                responsive: true,
                "order": [[ 0, "id" ]],
                "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
        });
        
        $('#dataTables-useraccounts').DataTable({
                responsive: true,
                "order": [[ 0, "id" ]],
                "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
        });
        
        $('#dataTables-apiaccounts').DataTable({
                responsive: true,
                "order": [[ 0, "id" ]],
                "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
        });
        
        $('#dataTables-apiaccountsstats').DataTable({
                responsive: true,
                "order": [[ 0, "id" ]],
                "oLanguage": { "sUrl": "../bower_components/datatables-plugins/i18n/English.json" }                
        });
        
        
        
    });
    </script>
    

	<!-- menu handler - Highlight selected menu item -->
	<script type="text/javascript">
	  	/* menu handler */
	  	/** add active class and stay opened when selected */
		var url = window.location;
		
		// for sidebar menu entirely but not cover treeview
		$('ul.sidebar-menu a').filter(function() {
			 return this.href == url;
		}).parent().addClass('active');
		
		// for treeview
		$('ul.treeview-menu a').filter(function() {
			 return this.href == url;
		}).parentsUntil(".sidebar-menu > .treeview-menu").addClass('active');
	</script>      

</body>
</html>
    
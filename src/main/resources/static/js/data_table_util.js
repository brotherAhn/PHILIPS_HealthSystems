/*<![CDATA[*/ 
var DataTableUtil = {
	dataTable : null,
	page_save : null,
	init : function(target,url,serach,columns,order) {
		DataTableUtil.dataTable =  target.DataTable({
			"search": {"search": ""},
			"pagingType": "full_numbers",
			"ajax": {
			    "url": url,
			    "dataSrc": function(res) {
		            var data = res.data;
		            $("#txtTotal").html(res.recordsTotal);
		            return data;
		        },
			    "type": "GET",
			    "data": serach
		    },
            "columns": columns,
            //order: order,
			aLengthMenu : [],
            ordering: false,
            searching: false,
            processing: false,
            serverSide: true,
			bFilter: false,
			bSort: false,
			"bLengthChange": false,
			bInfo: false,
			"language": {
				    "paginate": {
				      "first": '&laquo&laquo;',
				      "previous": "&laquo;",
				      "next": '&raquo;',
				      "last": "&raquo;&raquo;"
				    }
				},
			"lengthMenu":[6],
		});

		
		
	},
	Search : function () {
		DataTableUtil.dataTable.ajax.reload();
	},
	EnterSubmit : function (target) {
		target.keyup(function(){
			if(event.keyCode == 13) TckJS.PageUtil.goSearch();;
		})
	}
}
/* ]]> */
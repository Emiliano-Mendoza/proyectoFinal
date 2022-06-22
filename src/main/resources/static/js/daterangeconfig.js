	$(function() {

		  $('input[id="date_range"]').daterangepicker({
		      autoUpdateInput: false,
		      locale: {
		            "format": "DD/MM/YYYY",
		            "separator": "-",
		            "applyLabel": "Guardar",
		            "cancelLabel": "Cancelar",
		            "fromLabel": "Desde",
		            "toLabel": "Hasta",
		            "customRangeLabel": "Personalizar",
		            "daysOfWeek": [
		                "Do",
		                "Lu",
		                "Ma",
		                "Mi",
		                "Ju",
		                "Vi",
		                "Sa"
		            ],
		            "monthNames": [
		                "Enero",
		                "Febrero",
		                "Marzo",
		                "Abril",
		                "Mayo",
		                "Junio",
		                "Julio",
		                "Agosto",
		                "Setiembre",
		                "Octubre",
		                "Noviembre",
		                "Diciembre"
		            ],
		            "firstDay": 1
		        }
		  });

		  $('input[id="date_range"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
		  });

		  $('input[id="date_range"]').on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });

	});	
	
	$(function() {

		  $('input[id="date_range2"]').daterangepicker({
		      autoUpdateInput: false,
		      locale: {
		            "format": "DD/MM/YYYY",
		            "separator": "-",
		            "applyLabel": "Guardar",
		            "cancelLabel": "Cancelar",
		            "fromLabel": "Desde",
		            "toLabel": "Hasta",
		            "customRangeLabel": "Personalizar",
		            "daysOfWeek": [
		                "Do",
		                "Lu",
		                "Ma",
		                "Mi",
		                "Ju",
		                "Vi",
		                "Sa"
		            ],
		            "monthNames": [
		                "Enero",
		                "Febrero",
		                "Marzo",
		                "Abril",
		                "Mayo",
		                "Junio",
		                "Julio",
		                "Agosto",
		                "Setiembre",
		                "Octubre",
		                "Noviembre",
		                "Diciembre"
		            ],
		            "firstDay": 1
		        }
		  });

		  $('input[id="date_range2"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
		  });

		  $('input[id="date_range2"]').on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });

	});	
	
	$(function() {

		  $('input[id="date_range3"]').daterangepicker({
		      autoUpdateInput: false,
		      locale: {
		            "format": "DD/MM/YYYY",
		            "separator": "-",
		            "applyLabel": "Guardar",
		            "cancelLabel": "Cancelar",
		            "fromLabel": "Desde",
		            "toLabel": "Hasta",
		            "customRangeLabel": "Personalizar",
		            "daysOfWeek": [
		                "Do",
		                "Lu",
		                "Ma",
		                "Mi",
		                "Ju",
		                "Vi",
		                "Sa"
		            ],
		            "monthNames": [
		                "Enero",
		                "Febrero",
		                "Marzo",
		                "Abril",
		                "Mayo",
		                "Junio",
		                "Julio",
		                "Agosto",
		                "Setiembre",
		                "Octubre",
		                "Noviembre",
		                "Diciembre"
		            ],
		            "firstDay": 1
		        }
		  });

		  $('input[id="date_range3"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
		  });

		  $('input[id="date_range3"]').on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });

	});	
	
define(["ojs/ojconverterutils-i18n",'services/listviewService',"require", "exports", "knockout", "ojs/ojarraydataprovider", "text!./data.json", "ojs/ojknockout-keyset", 
"ojs/ojconverterutils-i18n", "ojs/ojconverter-datetime", "ojs/ojresponsiveutils", "ojs/ojresponsiveknockoututils",
 "ojs/ojknockout", "ojs/ojconverterutils-i18n", "ojs/ojconverter-datetime", "ojs/ojlistview", "ojs/ojlistviewdnd", 
 "ojs/ojlistitemlayout", "ojs/ojswipeactions", "ojs/ojmenu", "ojs/ojmodule", "ojs/ojtoolbar", "ojs/ojrefresher", 
 "ojs/ojbutton", "ojs/ojcheckboxset", "ojs/ojlabel", "ojs/ojlabelvalue", "ojs/ojselector", "ojs/ojswitch", 
 "ojs/ojselectcombobox", "ojs/ojavatar", "ojs/ojselectsingle","ojs/ojformlayout","ojs/ojdatetimepicker","ojs/ojmessages"
,'ojs/ojvalidation-datetime',"ojs/ojconverterutils-i18n"], 
 function (IntlConverterUtils,listviewService,require, exports, ko, ArrayDataProvider, taskData, ojknockout_keyset_1, ConverterUtils, DateTimeConverter,
     ResponsiveUtils, ResponsiveKnockoutUtils) {
    "use strict";

    class ViewModel {
        constructor() {

           
            

            this.smQuery = ResponsiveUtils.getFrameworkQuery(ResponsiveUtils.FRAMEWORK_QUERY_KEY.SM_ONLY);
            this.isSmall = ResponsiveKnockoutUtils.createMediaQueryObservable(this.smQuery);
            this.taskData = JSON.parse(taskData);
            this.taskDataArray = ko.observableArray();
            this.editdata = ko.observableArray();
            this.edittaskID= ko.observable();
            this.edittaskName= ko.observable();
            this.editdate= ko.observable();
            this.addtaskName= ko.observable();
            this.adddate= ko.observable();
           this.messagesDataprovider = ko.observable(new ArrayDataProvider([]));
          

            this.dataProvider = new ArrayDataProvider(this.taskDataArray, { keyAttributes: "taskId" });
            this.scrollPos = ko.observable();
            this.mode = ko.observable("view");
            this.selectedItems = new ojknockout_keyset_1.ObservableKeySet();
            this.isDeleteDisabled = ko.observable(true);
            //Checkes if taskDataArray is empty
            this.isDataEmpty = () => {
                if (this.taskDataArray().length == 0) {
                    return true;
                }
                else {
                    return false;
                }
            };
  
            
          
          

            // edit section start
            this.gotoContent = (event) => {
               // if (this.mode() == "view") {
                this.edittaskID(event.detail.context.data.taskId);
                this.edittaskName(event.detail.context.data.taskName);
                this.editdate(IntlConverterUtils.IntlConverterUtils.dateToLocalIso(new Date(event.detail.context.data.dateCompleted)));
                //this.editdate(IntlConverterUtils.dateToLocalIsoDateString( event.detail.context.data.dateCompleted));
                // this.editdate(IntlConverterUtils.IntlConverterUtils
                //     .dateToLocalIso(new Date(
                //         this.extractDate( event.detail.context.data.dateCompleted,'y'),
                //         this.extractDate( event.detail.context.data.dateCompleted,'m'),
                //         this.extractDate(event.detail.context.data.dateCompleted,'d')
                //     )));
                
                //this.editdata(event.detail.context.data);                    
                    //this.editdata =  JSON.stringify(selectedItem);                   
                    this.toggleDiv("listviewdiv");
                    this.toggleDiv("editdiv");
                        
                //}
               
            };
           


            //Specifes format for displaying date
            this.dateConverter = new DateTimeConverter.IntlDateTimeConverter({
                dateFormat: "short",
                formatType: "date",
                pattern : "y-M-d"
            });
           
            //Update task
            this.updateItem = () => {

                //When no taskId is entered, the entry is concidered invalid, and it goes back to previous page without adding a new item
                if (typeof this.edittaskName() === 'undefined' || typeof this.editdate() === 'undefined'
                || this.edittaskName() === "" || this.edittaskName() === null || this.editdate() === null) {
                    this.messagesDataprovider(new ArrayDataProvider([
                        {
                            severity: 'info',
                            summary: 'Task description and Task date, both are mandatory',
                            //detail: 'Message timeout set to:' ,
                            autoTimeout: 30000
                        }
                    ]))
                    return;
                }                
                else {
                    const newItem = {
                        id: this.edittaskID(),
                        taskCode: this.edittaskName(),                  
                        taskDate: this.editdate().split('T')[0]
                    };
                    this.createUpdateTask(newItem,'u');
                    this.goBackFromEdit();
                }                
             
            };
            //Delete task
            this.deleteItem = () => {
                const id = this.edittaskID();
                this.deleteTask(id);
                this.goBackFromEdit() ;
            };

            this.goBackFromEdit = () => {
                this.edittaskID(null);
                this.edittaskName(null);
                this.editdate(null);
                this.toggleDiv("listviewdiv");
                this.toggleDiv("editdiv");
            };

            //edit scetion end

           
                //Refresher
                this.refreshFunc = () => new Promise((resolve, reject) => {
                    // this timeout is just to simulate a delay so that
                    // the refresh panel does not close immediately

                    setTimeout(() => {
                        this.getAllTasks();
                        resolve();
                    }, 2000);
                    () => {
                        reject();
                    };
                });

            //add section start
            //Add task

            this.goBackFromAdd = () => {
                this.addtaskName(null);
                this.adddate(null);
                this.toggleDiv("listviewdiv");
                this.toggleDiv("adddiv");
            };

            this.addItem = () => {
                
                //When no taskId is entered, the entry is concidered invalid, and it goes back to previous page without adding a new item
                if (typeof this.addtaskName() === 'undefined' || typeof this.adddate() === 'undefined'
                 || this.addtaskName() === null || this.adddate() === null) {
                    this.messagesDataprovider(new ArrayDataProvider([
                        {
                            severity: 'info',
                            summary: 'Task description and Task date, both are mandatory',
                            //detail: 'Message timeout set to:' ,
                            autoTimeout: 30000
                        }
                    ]))
                    return;
                }                
                else {
                    const newItem = {
                        taskCode: this.addtaskName(),                    
                        taskDate: this.adddate()
                    };
                    this.createUpdateTask(newItem,'c');
                    this.goBackFromAdd();
                }
            };
            //add section end

            this.initialize = (args) => {
                this.router = args.parentRouter;
                if (args.params.scrollPos) {
                    this.scrollPos(JSON.parse(args.params.scrollPos));
                }
                else {
                    this.scrollPos({});
                }
            };
            //Add a new task to the listView, which would be appended to the begining of the listView
            this.addNewItem = () => {
                this.toggleDiv("listviewdiv");
                this.toggleDiv("adddiv");                
            };
           
            //Swipe Actions panel, swipe to the right provides an option to mark the task status as completed, 
            //swipe to left provides the option to delete the task.
            this.action = ko.observable();
            this.handleAction = (event, context) => {
                this.currentItem = context.data.taskId;
                this._doAction(event.target.value);
            };
            this._doAction = (action) => {
                if (action === "trash") {
                    this.deleteTask(this.currentItem);
                }
            };
         
         

            this.toggleDiv = (divname) =>{
                var x = document.getElementById(divname);
                if(x){
                if (x.style.display === "none") {
                  x.style.display = "block";
                } else {
                  x.style.display = "none";
                }
            }
            }

            //this.toggleDiv("editdiv");
            //this.toggleDiv("adddiv");
            this.getAllTasks = async () => {             
                const data = await listviewService.getAllTasks('task');                
                this.taskDataArray(data.map(e =>{
                    return {
                        taskId: e.id,
                        taskName: e.taskCode,
                        dateCompleted: e.taskDate
                    };
                }));
               
            },this;

            this.deleteTask = async (id) => {             
                const data = await listviewService.deleteTask('task',id);  
                this.messagesDataprovider(new ArrayDataProvider([
                    {
                        severity: 'info',
                        summary: 'Task Deleted',
                        //detail: 'Message timeout set to:' ,
                        autoTimeout: 30000
                    }
                ]))
                this.getAllTasks();
            },this;



            this.createUpdateTask = async (data,method) => {             
                const response = await listviewService.createUpdateTask('task',data);  
                if(typeof response.message === 'undefined')
                this.messagesDataprovider(new ArrayDataProvider([
                    {
                        severity: 'info',
                        summary: method === 'c'?'Task Created':'Task Updated',
                        //detail: 'Message timeout set to:' ,
                        autoTimeout: 30000
                    }
                ]));
                else
                this.messagesDataprovider(new ArrayDataProvider([
                    {
                        severity: 'info',
                        summary: 'Failed',
                        detail: response.message ,
                        autoTimeout: 30000
                    }
                ]));
                this.getAllTasks();
            },this;


             this.getAllTasks();

            //this.taskDataArray = await listviewService.getAllTasks('getAllTasks');

            this.extractDate = (date,part) => {
                if(part === 'y'){
                    return date.substring(date.indexOf("-",3) + 1);
                }else  if(part === 'm'){
                    return date.substring(date.indexOf("-") + 1,date.indexOf("-") + 2);
                }else  if(part === 'd'){
                    return date.substring(0,1);
                }
            }




        }

        

        
    }
    return new ViewModel();
});
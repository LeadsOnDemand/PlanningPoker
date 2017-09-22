import { ModuleWithProviders }    from '@angular/core';  
import { AppComponent }  from './app.component';
import { MasterComponent}  from './master/master.component';
import { AppComponentA }  from './activegames/app.componentA';
import { MygameComponent}  from './mygame/mygame.component';
import { JoinComponent}  from './join/join.component';
import { FormsModule }   from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

export const router:Routes = [
    
    { path:'master', component:MasterComponent},
    { path:'activegames', component:AppComponentA},
    { path:'join', component:JoinComponent},
    { path:'mygame', component:MygameComponent},
    { path:'',redirectTo:'master', pathMatch:'full'}
    
];

export const routes:ModuleWithProviders = RouterModule.forRoot(router);
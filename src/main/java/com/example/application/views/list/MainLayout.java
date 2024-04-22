package com.example.application.views.list;

import com.example.application.services.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    public MainLayout(SecurityService securityService)
    {
        this.securityService=securityService;
        createHeader();
        createDrawer();
    }


    private  void createHeader()
    {
        H1 logo=new H1("Conestoga College");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM
        );

        String u=securityService.getAuthenticatedUser().getUsername();
        Button logout=new Button("log out "+u,e->securityService.logout());


        var header =new HorizontalLayout(new DrawerToggle(),logo,logout);
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM
        );
        header.expand(logo);
        header.setWidthFull();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
    }
    private void createDrawer()
    {
        addToDrawer(new VerticalLayout(new RouterLink("List", ListView.class)));
        addToDrawer(new VerticalLayout(new RouterLink("Dashboard", DashboardView.class)));
    }
}

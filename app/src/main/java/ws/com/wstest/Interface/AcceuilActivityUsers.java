package ws.com.wstest.Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import ws.com.wstest.R;

public class AcceuilActivityUsers extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_opened,R.string.drawer_closed);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        final NavigationView nav_view=(NavigationView)findViewById(R.id.design_navigation_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();

                if(id==R.id.acceuil)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Acceuil",Toast.LENGTH_SHORT);
                }else
                if(id==R.id.créativités)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Créativités",Toast.LENGTH_SHORT);
                }else
                if(id==R.id.MaPage)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Ma page",Toast.LENGTH_SHORT);
                }else
                if(id==R.id.Mesarticles)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Mes articles",Toast.LENGTH_SHORT);
                }else
                if(id==R.id.MesCommentaires)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Mes commentaires",Toast.LENGTH_SHORT);
                }else
                if(id==R.id.MesFavoris)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Mes favoris",Toast.LENGTH_SHORT);
                }
                else
                if(id==R.id.Reclamation)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Reclamation",Toast.LENGTH_SHORT);
                }else
                if(id==R.id.Emprunts)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Mes emprunts",Toast.LENGTH_SHORT);
                }
                else
                if(id==R.id.Evenements)
                {
                    Toast.makeText(AcceuilActivityUsers.this,"Mes evenements",Toast.LENGTH_SHORT);
                }
                return true;
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_profil) {
            return true;
        }else
        if (id == R.id.action_deconnection) {
            return true;
        }else

            return super.onOptionsItemSelected(item);
    }
}

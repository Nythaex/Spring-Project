package com.example.demo.utils.event.eventListener;

import com.example.demo.models.entities.Region;
import com.example.demo.models.entities.Town;
import com.example.demo.models.entities.User;
import com.example.demo.models.enums.Role;
import com.example.demo.models.enums.UserType;
import com.example.demo.models.services.RegisterService;
import com.example.demo.services.RegionService;
import com.example.demo.services.TownService;
import com.example.demo.services.UserRoleService;
import com.example.demo.services.UserService;
import com.example.demo.utils.event.InitEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class initData {
    private final TownService townService;
    private final RegionService regionService;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public initData(TownService townService, RegionService regionService, UserRoleService userRoleService, UserService userService, PasswordEncoder encoder) {
        this.townService = townService;
        this.regionService = regionService;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.encoder = encoder;
    }


    @EventListener(InitEvent.class)
    public void onAppStart(){
        if (townService.isEmpty()) {

            regionService.addRegion(new Region().setName("Blagoevgrad"));
            townService.addTown(new Town().setName("Bansko").setRegion(regionService.getRegion("Blagoevgrad")));
            townService.addTown(new Town().setName("Blagoevgrad").setRegion(regionService.getRegion("Blagoevgrad")));
            townService.addTown(new Town().setName("Gotse Delchev").setRegion(regionService.getRegion("Blagoevgrad")));


            regionService.addRegion(new Region().setName("Burgas"));
            townService.addTown(new Town().setName("Burgas").setRegion(regionService.getRegion("Burgas")));
            townService.addTown(new Town().setName("Ruen").setRegion(regionService.getRegion("Burgas")));
            townService.addTown(new Town().setName("Pomorie").setRegion(regionService.getRegion("Burgas")));


            regionService.addRegion(new Region().setName("Dobrich"));
            townService.addTown(new Town().setName("Dobrich").setRegion(regionService.getRegion("Dobrich")));
            townService.addTown(new Town().setName("Dobrichka").setRegion(regionService.getRegion("Dobrich")));
            townService.addTown(new Town().setName("Balchik").setRegion(regionService.getRegion("Dobrich")));


            regionService.addRegion(new Region().setName("Gabrovo"));
            townService.addTown(new Town().setName("Gabrovo").setRegion(regionService.getRegion("Gabrovo")));
            townService.addTown(new Town().setName("Sevlievo").setRegion(regionService.getRegion("Gabrovo")));
            townService.addTown(new Town().setName("Tryavna").setRegion(regionService.getRegion("Gabrovo")));


            regionService.addRegion(new Region().setName("Haskovo"));
            townService.addTown(new Town().setName("Haskovo").setRegion(regionService.getRegion("Haskovo")));
            townService.addTown(new Town().setName("Svilengrad").setRegion(regionService.getRegion("Haskovo")));
            townService.addTown(new Town().setName("Dimitrovgrad").setRegion(regionService.getRegion("Haskovo")));


            regionService.addRegion(new Region().setName("Kardzhali"));
            townService.addTown(new Town().setName("Kardzhali").setRegion(regionService.getRegion("Kardzhali")));
            townService.addTown(new Town().setName("Kirkovo").setRegion(regionService.getRegion("Kardzhali")));
            townService.addTown(new Town().setName("Krumovgrad").setRegion(regionService.getRegion("Kardzhali")));


            regionService.addRegion(new Region().setName("Kyustendil"));
            townService.addTown(new Town().setName("Kyustendil").setRegion(regionService.getRegion("Kyustendil")));
            townService.addTown(new Town().setName("Dupnitsa").setRegion(regionService.getRegion("Kyustendil")));
            townService.addTown(new Town().setName("Bobov dol").setRegion(regionService.getRegion("Kyustendil")));


            regionService.addRegion(new Region().setName("Lovech"));
            townService.addTown(new Town().setName("Lovech").setRegion(regionService.getRegion("Lovech")));
            townService.addTown(new Town().setName("Troyan").setRegion(regionService.getRegion("Lovech")));
            townService.addTown(new Town().setName("Teteven").setRegion(regionService.getRegion("Lovech")));


            regionService.addRegion(new Region().setName("Montana"));
            townService.addTown(new Town().setName("Montana").setRegion(regionService.getRegion("Montana")));
            townService.addTown(new Town().setName("Lom").setRegion(regionService.getRegion("Montana")));
            townService.addTown(new Town().setName("Berkovitsa").setRegion(regionService.getRegion("Montana")));


            regionService.addRegion(new Region().setName("Pazardzhik"));
            townService.addTown(new Town().setName("Pazardzhik").setRegion(regionService.getRegion("Pazardzhik")));
            townService.addTown(new Town().setName("Velingrad").setRegion(regionService.getRegion("Pazardzhik")));
            townService.addTown(new Town().setName("Septemvri").setRegion(regionService.getRegion("Pazardzhik")));


            regionService.addRegion(new Region().setName("Pernik"));
            townService.addTown(new Town().setName("Pernik").setRegion(regionService.getRegion("Pernik")));
            townService.addTown(new Town().setName("Chervena Mogila").setRegion(regionService.getRegion("Pernik")));
            townService.addTown(new Town().setName("Strezimirovci").setRegion(regionService.getRegion("Pernik")));


            regionService.addRegion(new Region().setName("Pleven"));
            townService.addTown(new Town().setName("Pleven").setRegion(regionService.getRegion("Pleven")));
            townService.addTown(new Town().setName("Cherven Bryag").setRegion(regionService.getRegion("Pleven")));
            townService.addTown(new Town().setName("Levski").setRegion(regionService.getRegion("Pleven")));


            regionService.addRegion(new Region().setName("Plovdiv"));
            townService.addTown(new Town().setName("Plovdiv").setRegion(regionService.getRegion("Plovdiv")));
            townService.addTown(new Town().setName("Asenovgrad").setRegion(regionService.getRegion("Plovdiv")));
            townService.addTown(new Town().setName("Karlovo").setRegion(regionService.getRegion("Plovdiv")));


            regionService.addRegion(new Region().setName("Razgrad"));
            townService.addTown(new Town().setName("Razgrad").setRegion(regionService.getRegion("Razgrad")));
            townService.addTown(new Town().setName("Isperih").setRegion(regionService.getRegion("Razgrad")));
            townService.addTown(new Town().setName("Kubrat").setRegion(regionService.getRegion("Razgrad")));


            regionService.addRegion(new Region().setName("Ruse"));
            townService.addTown(new Town().setName("Ruse").setRegion(regionService.getRegion("Ruse")));
            townService.addTown(new Town().setName("Byala").setRegion(regionService.getRegion("Ruse")));
            townService.addTown(new Town().setName("Vetovo").setRegion(regionService.getRegion("Ruse")));


            regionService.addRegion(new Region().setName("Shumen"));
            townService.addTown(new Town().setName("Shumen").setRegion(regionService.getRegion("Shumen")));
            townService.addTown(new Town().setName("Veliki Preslav").setRegion(regionService.getRegion("Shumen")));
            townService.addTown(new Town().setName("Novi Pazar").setRegion(regionService.getRegion("Shumen")));


            regionService.addRegion(new Region().setName("Silistra"));
            townService.addTown(new Town().setName("Silistra").setRegion(regionService.getRegion("Silistra")));
            townService.addTown(new Town().setName("Dulovo").setRegion(regionService.getRegion("Silistra")));
            townService.addTown(new Town().setName("Tutrakan").setRegion(regionService.getRegion("Silistra")));


            regionService.addRegion(new Region().setName("Sliven"));
            townService.addTown(new Town().setName("Sliven").setRegion(regionService.getRegion("Sliven")));
            townService.addTown(new Town().setName("Nova Zagora").setRegion(regionService.getRegion("Sliven")));
            townService.addTown(new Town().setName("Kotel").setRegion(regionService.getRegion("Sliven")));


            regionService.addRegion(new Region().setName("Smolyan"));
            townService.addTown(new Town().setName("Smolyan").setRegion(regionService.getRegion("Smolyan")));
            townService.addTown(new Town().setName("Devin").setRegion(regionService.getRegion("Smolyan")));
            townService.addTown(new Town().setName("Madan").setRegion(regionService.getRegion("Smolyan")));


            regionService.addRegion(new Region().setName("Sofia-city"));
            townService.addTown(new Town().setName("Sofia").setRegion(regionService.getRegion("Sofia-city")));


            regionService.addRegion(new Region().setName("Sofia"));
            townService.addTown(new Town().setName("Botevgrad").setRegion(regionService.getRegion("Sofia")));
            townService.addTown(new Town().setName("Samokov").setRegion(regionService.getRegion("Sofia")));
            townService.addTown(new Town().setName("Svoge").setRegion(regionService.getRegion("Sofia")));


            regionService.addRegion(new Region().setName("Stara Zagora"));
            townService.addTown(new Town().setName("Stara Zagora").setRegion(regionService.getRegion("Stara Zagora")));
            townService.addTown(new Town().setName("Kazanlak").setRegion(regionService.getRegion("Stara Zagora")));
            townService.addTown(new Town().setName("Chirpan").setRegion(regionService.getRegion("Stara Zagora")));


            regionService.addRegion(new Region().setName("Targovishte"));
            townService.addTown(new Town().setName("Targovishte").setRegion(regionService.getRegion("Targovishte")));
            townService.addTown(new Town().setName("Popovo").setRegion(regionService.getRegion("Targovishte")));
            townService.addTown(new Town().setName("Omurtag").setRegion(regionService.getRegion("Targovishte")));


            regionService.addRegion(new Region().setName("Varna"));
            townService.addTown(new Town().setName("Varna").setRegion(regionService.getRegion("Varna")));
            townService.addTown(new Town().setName("Aksakovo").setRegion(regionService.getRegion("Varna")));
            townService.addTown(new Town().setName("Provadia").setRegion(regionService.getRegion("Varna")));


            regionService.addRegion(new Region().setName("Veliko Tarnovo"));
            townService.addTown(new Town().setName("Veliko Tarnovo").setRegion(regionService.getRegion("Veliko Tarnovo")));
            townService.addTown(new Town().setName("Svishtov").setRegion(regionService.getRegion("Veliko Tarnovo")));
            townService.addTown(new Town().setName("Gorna Oryahovitsa").setRegion(regionService.getRegion("Veliko Tarnovo")));


            regionService.addRegion(new Region().setName("Vidin"));
            townService.addTown(new Town().setName("Vidin").setRegion(regionService.getRegion("Vidin")));
            townService.addTown(new Town().setName("Belogradchik").setRegion(regionService.getRegion("Vidin")));
            townService.addTown(new Town().setName("Dimovo").setRegion(regionService.getRegion("Vidin")));


            regionService.addRegion(new Region().setName("Vratsa"));
            townService.addTown(new Town().setName("Vratsa").setRegion(regionService.getRegion("Vratsa")));
            townService.addTown(new Town().setName("Mezdra").setRegion(regionService.getRegion("Vratsa")));
            townService.addTown(new Town().setName("Byala Slatina").setRegion(regionService.getRegion("Vratsa")));


            regionService.addRegion(new Region().setName("Yambol"));
            townService.addTown(new Town().setName("Yambol").setRegion(regionService.getRegion("Yambol")));
            townService.addTown(new Town().setName("Tundzha ").setRegion(regionService.getRegion("Yambol")));
            townService.addTown(new Town().setName("Elhovo").setRegion(regionService.getRegion("Yambol")));


            //addRoles
            userRoleService.addRole(Role.ADMIN);
            userRoleService.addRole(Role.USER);
            userRoleService.addRole(Role.SHELTER);

            //add users
            userService.register(new RegisterService().setTown("Yambol").setPassword("12345").setUsername("admin").setEmail("admin@gmail.com").setUserType(UserType.USER));
            User byId = userService.getById(1L);
            byId.getRoles().add(userRoleService.getRoleById(1L));
            userService.save(byId);
            userService.register(new RegisterService().setTown("Yambol").setPassword("12345").setUsername("user").setEmail("user@gmail.com").setUserType(UserType.USER));
            userService.register(new RegisterService().setTown("Yambol").setPassword("12345").setUsername("banned").setEmail("banned@gmail.com").setUserType(UserType.SHELTER));
            userService.save(userService.getById(3L).setBanned(true));
            userService.register(new RegisterService().setTown("Yambol").setPassword("12345").setUsername("shelter").setEmail("shelter@gmail.com").setUserType(UserType.SHELTER));

        }
    }
}

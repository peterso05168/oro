package hk.oro.iefas.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.hazelcast.HazelcastSessionRepository;

import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.session.security.SpringSessionBackedSessionRegistry;
import hk.oro.iefas.web.core.filter.CustomUserFilter;
import hk.oro.iefas.web.core.security.authentication.CustomAuthenticationFailureHandler;
import hk.oro.iefas.web.core.security.authentication.CustomAuthenticationSuccessHandler;
import hk.oro.iefas.web.core.security.session.CustomInvalidSessionStrategy;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String ROLE_SUPERUSER = "SUPERUSER";
    public final static String LOGIN_PAGE = "/oro/admin/index/login.xhtml";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private HazelcastSessionRepository sessionRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler(LOGIN_PAGE + "?error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requiresChannel().anyRequest().requiresSecure();

        http.csrf().disable().headers().frameOptions().sameOrigin();

        http.addFilterBefore(new CustomUserFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin().loginPage(LOGIN_PAGE).permitAll().successHandler(successHandler())
            .failureHandler(failureHandler());

        http.logout().logoutSuccessUrl(LOGIN_PAGE).invalidateHttpSession(true).deleteCookies("JSESSIONID");

        http.exceptionHandling().accessDeniedPage("/error/denied.xhtml");

        http.sessionManagement().sessionFixation().newSession()
            .invalidSessionStrategy(new CustomInvalidSessionStrategy(LOGIN_PAGE)).maximumSessions(1)
            .expiredUrl(LOGIN_PAGE).maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry());

        http.authorizeRequests().antMatchers("/oro/admin/dashboard/**", "/oro/admin/my-profile/**")
            .fullyAuthenticated();

        // Outsider
        http.authorizeRequests().antMatchers("/oro/admin/case/maintenance/outsider/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_OE, PrivilegeConstant.PRIVILEGE_OM);

        // Case Detail Enquiry
        http.authorizeRequests().antMatchers("/oro/admin/case/case-detail-enquiry/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CDE);

        // Upload Data From ORMIS
        http.authorizeRequests().antMatchers("/oro/admin/case/data-interface/upload-data-from-ormis/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_UDFO);

        // Proof of Debt Form
        http.authorizeRequests().antMatchers("/oro/admin/case/proof-of-debt/form/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_PODF);

        // Proof of Debt Enquiry
        http.authorizeRequests().antMatchers("/oro/admin/case/proof-of-debt/enquiry/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_PODERO, PrivilegeConstant.PRIVILEGE_PODE);

        // Analysis Code Maintenance
        http.authorizeRequests().antMatchers("/oro/admin/ledger/maintenance/analysis-code/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ACE, PrivilegeConstant.PRIVILEGE_ACM);

        // Control Account Maintenance
        http.authorizeRequests().antMatchers("/oro/admin/ledger/maintenance/control-account/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CAE, PrivilegeConstant.PRIVILEGE_CAM);

        // Payment File
        http.authorizeRequests().antMatchers("/oro/admin/ledger/payment-handling/payment-file/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_PFE);

        // Create Bank Transfer File
        http.authorizeRequests().antMatchers("/oro/admin/ledger/payment-handling/create-bank-transfer-file/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CBTF);

        // Generate Cheque File
        http.authorizeRequests().antMatchers("/oro/admin/ledger/payment-handling/generate-cheque-file/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_GCF);

        // Roster
        http.authorizeRequests().antMatchers("/oro/admin/ledger/payment-handling/roster/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_RM, PrivilegeConstant.PRIVILEGE_RE);

        // Transfer to BEA/GR
        http.authorizeRequests().antMatchers("/oro/admin/ledger/payment-handling/transfer-to-gr-bea/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_TBGE,
                PrivilegeConstant.PRIVILEGE_TBGM);

        // Bulk Voucher Import
        http.authorizeRequests().antMatchers("/oro/admin/ledger/voucher-handling/bulk-voucher-import/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_BVIE,
                PrivilegeConstant.PRIVILEGE_BVIM);

        // Payment Voucher
        http.authorizeRequests().antMatchers("/oro/admin/ledger/voucher-handling/payment-voucher/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_PVM, PrivilegeConstant.PRIVILEGE_PVE);

        // Receipt Voucher
        http.authorizeRequests().antMatchers("/oro/admin/ledger/voucher-handling/receipt-voucher/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_RVE, PrivilegeConstant.PRIVILEGE_RVM);

        // Journal Voucher
        http.authorizeRequests().antMatchers("/oro/admin/ledger/voucher-handling/journal-voucher/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_JVE, PrivilegeConstant.PRIVILEGE_JVM);

        // Cheque Registration
        http.authorizeRequests().antMatchers("/oro/admin/ledger/cheque-handling/cheque-registration/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CR);

        // Bulk Reversal
        http.authorizeRequests().antMatchers("/oro/admin/ledger/cheque-handling/bulk-reversal/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_BRE, PrivilegeConstant.PRIVILEGE_BRM);

        // Incoming Cheque
        http.authorizeRequests().antMatchers("/oro/admin/ledger/cheque-handling/incoming-cheque/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ICE, PrivilegeConstant.PRIVILEGE_ICM);

        // Outgoing Cheque
        http.authorizeRequests().antMatchers("/oro/admin/ledger/cheque-handling/outgoing-cheque/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_OCE, PrivilegeConstant.PRIVILEGE_OCM);

        // Deposit Form
        http.authorizeRequests().antMatchers("/oro/admin/ledger/deposit-handling/form/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_RDR);

        // Deposit Enquiry
        http.authorizeRequests().antMatchers("/oro/admin/ledger/deposit-handling/enquiry/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_RDE, PrivilegeConstant.PRIVILEGE_RDM);

        // Instruction Memo List
        http.authorizeRequests().antMatchers("/oro/admin/release/instruction-memo-list/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_IMLE, PrivilegeConstant.PRIVILEGE_IMLM);

        // Historical Case List
        http.authorizeRequests().antMatchers("/oro/admin/release/historical-case-list/**").hasAnyAuthority(
            PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_HCLE, PrivilegeConstant.PRIVILEGE_HCLM);

        // OR's Fee
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/maintenance/ors-fees/search.xhtml",
                "/oro/admin/dividend/maintenance/ors-fees/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ORFE,
                PrivilegeConstant.PRIVILEGE_ORFM);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/ors-fees/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ORFM);

        // Analysis Code for OR's Fees to be Charged
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/maintenance/ors-fee-to-be-charged/search.xhtml",
                "/oro/admin/dividend/maintenance/ors-fee-to-be-charged/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ACORE,
                PrivilegeConstant.PRIVILEGE_ACORM);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/ors-fee-to-be-charged/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ACORM);

        // Ground Code
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/maintenance/ground-code/search.xhtml",
                "/oro/admin/dividend/maintenance/ground-code/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_GCE,
                PrivilegeConstant.PRIVILEGE_GCM);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/ground-code/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_GCM);

        // Withheld Reason
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/maintenance/withheld-reasons/search.xhtml",
                "/oro/admin/dividend/maintenance/withheld-reasons/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_WRE,
                PrivilegeConstant.PRIVILEGE_WRM);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/withheld-reasons/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_WRM);

        // Gazettes
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/maintenance/gazette/search.xhtml",
                "/oro/admin/dividend/maintenance/gazette/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_GDE,
                PrivilegeConstant.PRIVILEGE_GDM);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/gazette/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_GDM);

        // Common Creditor
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/maintenance/common-creditor/search.xhtml",
                "/oro/admin/dividend/maintenance/common-creditor/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CCE,
                PrivilegeConstant.PRIVILEGE_CCM);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/common-creditor/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CCM);

        // Dividend Parameter
        http.authorizeRequests().antMatchers("/oro/admin/dividend/maintenance/other-parameter/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_DP);

        // Employee WILON & Severance Pay
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/creditor-registration/employee-wilon-severance-pay/search.xhtml",
                "/oro/admin/dividend/creditor-registration/employee-wilon-severance-pay/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_EWSPE,
                PrivilegeConstant.PRIVILEGE_EWSPM);
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/creditor-registration/employee-wilon-severance-pay/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_EWSPM);

        // Adjudication
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/creditor-registration/adjudication/search.xhtml",
                "/oro/admin/dividend/creditor-registration/adjudication/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ADJE,
                PrivilegeConstant.PRIVILEGE_ADJS, PrivilegeConstant.PRIVILEGE_ADJA);
        http.authorizeRequests().antMatchers("/oro/admin/dividend/creditor-registration/adjudication/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ADJS,
                PrivilegeConstant.PRIVILEGE_ADJA);

        // Interest Trial Adjudication
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/creditor-registration/interest-trial-adjudication/search.xhtml",
                "/oro/admin/dividend/creditor-registration/interest-trial-adjudication/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ITAE,
                PrivilegeConstant.PRIVILEGE_ITAS, PrivilegeConstant.PRIVILEGE_ITAA);
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/creditor-registration/interest-trial-adjudication/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_ITAS,
                PrivilegeConstant.PRIVILEGE_ITAA);

        // Payment Percentages Adjustment
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/payment-dividend-processing/payment-percentages-adjustment/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_PPAE,
                PrivilegeConstant.PRIVILEGE_PPAM);

        // OR's Fees Computation & Appropriation Account
        http.authorizeRequests().antMatchers(
            "/oro/admin/dividend/payment-dividend-processing/ors-fees-computation-appropriation-account/search.xhtml",
            "/oro/admin/dividend/payment-dividend-processing/ors-fees-computation-appropriation-account/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_OEFCE,
                PrivilegeConstant.PRIVILEGE_OEFCS, PrivilegeConstant.PRIVILEGE_OEFCA);
        http.authorizeRequests()
            .antMatchers(
                "/oro/admin/dividend/payment-dividend-processing/ors-fees-computation-appropriation-account/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_OEFCS,
                PrivilegeConstant.PRIVILEGE_OEFCA);

        // Dividend Interest Schedule
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/payment-dividend-processing/dividend-interest-schedule/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_DSE,
                PrivilegeConstant.PRIVILEGE_DSS, PrivilegeConstant.PRIVILEGE_DSC, PrivilegeConstant.PRIVILEGE_DSA);

        // Dividend Interest Cheuqe
        http.authorizeRequests()
            .antMatchers("/oro/admin/dividend/payment-dividend-processing/dividend-interest-cheque/**").hasAnyAuthority(
                PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_DICE, PrivilegeConstant.PRIVILEGE_DICR);

        // Currency Rate
        http.authorizeRequests()
            .antMatchers("/oro/admin/funds/maintenance/currency-rate/search.xhtml",
                "/oro/admin/funds/maintenance/currency-rate/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CRE,
                PrivilegeConstant.PRIVILEGE_CRM);
        http.authorizeRequests().antMatchers("/oro/admin/funds/maintenance/currency-rate/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CRM);

        // Bank Account
        http.authorizeRequests()
            .antMatchers("/oro/admin/funds/maintenance/bank-account/search.xhtml",
                "/oro/admin/funds/maintenance/bank-account/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_BAE,
                PrivilegeConstant.PRIVILEGE_BAM);
        http.authorizeRequests().antMatchers("/oro/admin/funds/maintenance/bank-account/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_BAM);

        // Deposit Target
        http.authorizeRequests()
            .antMatchers("/oro/admin/funds/maintenance/deposit-target/search.xhtml",
                "/oro/admin/funds/maintenance/deposit-target/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_DTE,
                PrivilegeConstant.PRIVILEGE_DTM);
        http.authorizeRequests().antMatchers("/oro/admin/funds/maintenance/deposit-target/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_DTM);

        // Funds Parameter
        http.authorizeRequests().antMatchers("/oro/admin/funds/maintenance/funds-parameter/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_FP);

        // Upload Bank Rate
        http.authorizeRequests()
            .antMatchers("/oro/admin/funds/investment/upload-bank-rate/search.xhtml",
                "/oro/admin/funds/investment/upload-bank-rate/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_UBRE,
                PrivilegeConstant.PRIVILEGE_UBRM);
        http.authorizeRequests().antMatchers("/oro/admin/funds/investment/upload-bank-rate/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_UBRM);

        // Cash Requirement
        http.authorizeRequests().antMatchers("/oro/admin/funds/investment/cash-requirement/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_CASHR);

        // Investment Instruction
        http.authorizeRequests()
            .antMatchers("/oro/admin/funds/investment/investment-instruction/search.xhtml",
                "/oro/admin/funds/investment/investment-instruction/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_IIE,
                PrivilegeConstant.PRIVILEGE_IIM);
        http.authorizeRequests().antMatchers("/oro/admin/funds/investment/investment-instruction/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_IIM);

        // Funds Available For Placing Deposits
        http.authorizeRequests()
            .antMatchers("/oro/admin/funds/investment/funds-available/search.xhtml",
                "/oro/admin/funds/investment/funds-available/result.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_FAPDE,
                PrivilegeConstant.PRIVILEGE_FAPDM);
        http.authorizeRequests().antMatchers("/oro/admin/funds/investment/funds-available/edit.xhtml")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_FAPDM);

        // Daily Investment
        http.authorizeRequests().antMatchers("/oro/admin/funds/investment/daily-investment-confirmation/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_DIE,
                PrivilegeConstant.PRIVILEGE_DIM);

        // Upload Bank File
        http.authorizeRequests().antMatchers("/oro/admin/bank-rec/upload-bank-file/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_UBF);

        // Undefined BC
        http.authorizeRequests().antMatchers("/oro/admin/bank-rec/undefined-bc/**")
            .hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA, PrivilegeConstant.PRIVILEGE_UBC);

        // System
        http.authorizeRequests().antMatchers("/oro/admin/system/**").hasAnyAuthority(PrivilegeConstant.PRIVILEGE_SA);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/javax.faces.resources/**", "/index.html");
    }

}

package com.orgsync.api.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.orgsync.api.MembershipLogsResource;
import com.orgsync.api.Resources;
import com.orgsync.api.model.membership_logs.MembershipLogEntry;
import com.orgsync.api.model.membership_logs.MembershipLogEntryRequest;
import com.typesafe.config.Config;

public class MembershipLogsIntergrationTest extends BaseIntegrationTest<MembershipLogsResource> {

    public MembershipLogsIntergrationTest() {
        super(Resources.MEMBERSHIP_LOGS);
    }

    @AfterClass
    public static void cleanup() {
        BaseIntegrationTest.cleanup(Resources.MEMBERSHIP_LOGS);
    }

    @Test
    public void testGetMembershipLogEntries() throws Exception {
        List<MembershipLogEntry> result = getResult(getResource().getLogEntries(new MembershipLogEntryRequest()));

        assertEquals(2, result.size());
    }

    @Test
    public void testGetMembershipLogForAcount() throws Exception {
        Config userConfig = DbTemplate.getList("users").get(0);
        Config portalConfig = DbTemplate.getList("portals").get(0);

        List<MembershipLogEntry> result = getResult(getResource().getLogEntries(
                new MembershipLogEntryRequest().setAccountId(userConfig.getInt("id"))));

        assertEquals(1, result.size());

        MembershipLogEntry entry = result.get(0);
        assertEquals("Join", entry.getAction());
        assertEquals(userConfig.getInt("id"), entry.getAccountId());
        assertEquals(portalConfig.getInt("id"), entry.getOrgId());
    }

}

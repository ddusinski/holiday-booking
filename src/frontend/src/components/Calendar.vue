<template>
    <h1>{{ helloMsg }}</h1>
    <h2> Calendar</h2>
    <button v-tooltip="'You have new messages.'">Button</button>
    <!-- <button v-tooltip="tooltipContent"/> -->
    <!-- <h2>Calendar</h2> -->
    <div class='demo-app-main'>
        <FullCalendar :options="calendarOptions">
            <v-tooltip bottom color="teal">
                <template v-slot:activator="{ on, attrs }">
                    <div style="min-height:20px;" v-bind="attrs" v-on="on">
                    </div>
                </template>
                <div style="text-align:left;">
                    Description: {{ arg.event.extendedProps.description }}<br />
                    Project {{ arg.event.extendedProps.projectName }}<br />
                </div>
            </v-tooltip>
        </FullCalendar>
    </div>
    <br>
</template>


<script>

import { defineComponent } from "vue";
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
// import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'




export default {
    components: {
        FullCalendar, // make the <FullCalendar> tag available
    },
    // name: 'UserList',
    data() {
        return {
            show: false,
            helloMsg: '',
            tooltipContent: 'Welcome message',
            calendarOptions: {
                plugins: [dayGridPlugin, interactionPlugin],
                initialView: 'dayGridMonth',
                events: [
                    // { title: 'event 1', date: '2023-05-19' },
                    // { title: 'event 2', date: '2023-05-21' }
                ],
                dateClick: this.handleDateClick,
                // eventDidMount: function (info) {
                //     var tooltip = new Tooltip(info.el, {
                //         title: info.event.extendedProps.description,
                //         placement: 'top',
                //         trigger: 'hover',
                //         container: 'body'
                //     });
                // },
            }
        }
    },
    methods: {
        getEvents() {
            fetch("/api/events/get")
                .then((response) => response.text())
                .then((data) => {
                    this.calendarOptions.events = JSON.parse(data);
                    // console.log(this.calendarOptions.events);
                });
        },
        handleDateClick: function (arg) {
            alert('date click! ' + arg.dateStr)
        }
    },
    mounted() {
        this.getEvents();
    }
}
</script>
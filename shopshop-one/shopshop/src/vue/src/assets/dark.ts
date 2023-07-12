// import {
//     BasicColorSchema,
//     UseColorModeOptions,
// } from '@vueuse/core'
// import {WritableComputedRef} from "vue";
//
// export const isDark = useDark({
// storageKey: 'vitepress-theme-appearance',
// })
//
// export interface UseDarkOptions
//     extends Omit<UseColorModeOptions<BasicColorSchema>, "modes" | "onChanged"> {
//     /**
//      * Value applying to the target element when isDark=true
//      *
//      * @default 'dark'
//      */
//     valueDark?: string
//     /**
//      * Value applying to the target element when isDark=false
//      *
//      * @default ''
//      */
//     valueLight?: string
//     /**
//      * A custom handler for handle the updates.
//      * When specified, the default behavior will be overridden.
//      *
//      * @default undefined
//      */
//     onChanged?: (
//         isDark: boolean,
//         defaultHandler: (mode: BasicColorSchema) => void,
//         mode: BasicColorSchema
//     ) => void
// }
// /**
//  * Reactive dark mode with auto data persistence.
//  *
//  * @see https://vueuse.org/useDark
//  * @param options
//  */
// export declare function useDark(
//     options?: UseDarkOptions
// ): WritableComputedRef<boolean>
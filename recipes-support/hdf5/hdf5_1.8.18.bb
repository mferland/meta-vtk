DESCRIPTION = "The HDF5 library (Hierarchical Data Format)"
HOMEPAGE = "http://www.hdfgroup.org"
SECTION = "libs"
LICENSE = "license_hdf5"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ad878f669af233d3b861433c71857e4"

SRC_URI = "https://support.hdfgroup.org/ftp/HDF5/current18/src/${PN}-${PV}.tar.bz2 \
file://acsite_fixes.patch \
file://configure_fix.patch \
file://makefile_crosscompile_fixes.patch \
file://H5lib_settings.c \
file://H5Tinit.c \
"

SRC_URI[md5sum] = "29117bf488887f89888f9304c8ebea0b"
SRC_URI[sha256sum] = "01c6deadf4211f86922400da82c7a8b5b50dc8fc1ce0b5912de3066af316a48c"

inherit autotools pkgconfig

HDF5_CACHED_CONFIGUREVARS = "${@base_contains('DISTRO_FEATURES', 'largefile', 'hdf5_cv_have_lfs=yes', 'hdf5_cv_have_lfs=no', d)} \
hdf5_cv_gettimeofday_tz=yes \
hdf5_cv_vsnprintf_works=yes \
hdf5_cv_system_scope_threads=yes \
hdf5_cv_direct_io=no \
hdf5_cv_ldouble_to_integer_works=yes \
hdf5_cv_ulong_to_float_accurate=yes \
hdf5_cv_fp_to_ullong_accurate=yes \
hdf5_cv_fp_to_ullong_right_maximum=yes \
hdf5_cv_ldouble_to_uint_accurate=yes \
hdf5_cv_fp_to_integer_overflow_works=yes \
hdf5_cv_ldouble_to_long_special=no \
hdf5_cv_long_to_ldouble_special=no \
hdf5_cv_ldouble_to_llong_accurate=yes \
hdf5_cv_llong_to_ldouble_correct=yes \
"

CACHED_CONFIGUREVARS_arm += "${HDF5_CACHED_CONFIGUREVARS} hdf5_cv_ullong_to_ldouble_precision=no"
CACHED_CONFIGUREVARS_i586 += "${HDF5_CACHED_CONFIGUREVARS} hdf5_cv_ullong_to_ldouble_precision=yes"
CACHED_CONFIGUREVARS_i686 += "${HDF5_CACHED_CONFIGUREVARS} hdf5_cv_ullong_to_ldouble_precision=yes"
CACHED_CONFIGUREVARS_x86-64 += "${HDF5_CACHED_CONFIGUREVARS} hdf5_cv_ullong_to_ldouble_precision=yes"

EXTRA_OECONF += "--enable-shared --disable-static"

PACKAGES += "${PN}-examples"
FILES_${PN}-examples = "${datadir}"
FILES_${PN}-dev += "${libdir}/libhdf5.settings"

do_configure_prepend() {
    cp ${WORKDIR}/H5lib_settings.c ${S}/src/H5lib_settings.c
    cp ${WORKDIR}/H5Tinit.c ${S}/src/H5Tinit.c
}
